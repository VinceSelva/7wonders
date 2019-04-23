package client;

import game.Card;
import game.DeckAgeI;
import game.Participant;
import game.Wonder;
import game.WonderList;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class Client {
    private Socket connection;
    int turnNb = 0;
    private Participant player = new Participant(null);
    private WonderList wonders = new WonderList();
    private DeckAgeI deckAgeI = new DeckAgeI();
    private ClientType type;
    
    public Client(String serverURL, String name, ClientType type) {
        try {
            connection = IO.socket(serverURL);
            this.type = type;

            connection.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    player.setName(name);
                    connection.emit("identification", name);

                    System.out.println("Client - Connected to server with username " + name);
                }
            });

            connection.on("playerWonder", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String wonderName = (String)args[0];
                    Wonder wonder = wonders.nameToWonder(wonderName);

                    player.setWonder(wonder);

                    // Signale au serveur que le joueur est prêt si il a des cartes et qu'il vient de recevoir une merveille
                    if (player.getHand().size() > 0 && turnNb == 0) {
                        connection.emit("ready", "");
                    }
                }
            });

            connection.on("playerCards", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        JSONArray cards = new JSONArray((String)args[0]);
                        ArrayList<Card> cardList = new ArrayList<>();

                        for (int i = 0; i < cards.length(); i++) {
                            Card card = deckAgeI.nameToCard(cards.getString(i));
                            cardList.add(card);
                        }

                        player.setHand(cardList);
                    } catch (Exception e) {
                        System.out.println("Client - JSON error - " + e.getMessage());
                    }

                    // Signale au serveur que le joueur est prêt si il a une merveille et qu'il vient de recevoir les cartes
                    if (player.getWonder() != null && turnNb == 0) {
                        connection.emit("ready", "");
                    }
                }
            });

            connection.on("turn", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    play();
                    turnNb++;
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void play() {
        Card card;

        switch (type) {
            case PLAY_FIRST_CARD: {
                card = player.getHand().get(0);

                if (player.canBuild(card)) {
                    player.build(card);
                    connection.emit("build", card.getName());
                } else {
                    player.discard(card);
                    connection.emit("discard", card.getName());
                }

                break;
            }

            case TRY_ONE_CARD: {
                int index = player.bestPlay();
                
                if (index == -1) {
                    card = player.getHand().get(0);
                    player.discard(card);
                    connection.emit("discard", card.getName());
                } else {
                    card = player.getHand().get(index);
                    player.build(card);
                    connection.emit("build", card.getName());
                }

                break;
            }
        }
    }

    public void connect() {
        connection.connect();
    }
}