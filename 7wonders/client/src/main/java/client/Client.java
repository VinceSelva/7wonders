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
    private String playerName;
    int turnNb = 0;
    boolean ready = false;
    private Participant player = new Participant(null);
    private WonderList wonders = new WonderList();
    private DeckAgeI deckAgeI = new DeckAgeI();
    
    public Client(String serverURL, String playerName) {
        try {
            connection = IO.socket(serverURL);

            connection.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    connection.emit("identification", playerName);
                    setName(playerName);

                    System.out.println("Connected to server with username " + playerName);
                }
            });

            connection.on("playerWonder", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String wonderName = (String)args[0];
                    Wonder wonder = wonders.nameToWonder(wonderName);

                    player.setWonder(wonder);

                    // Signale au serveur que le joueur est prêt si il a des cartes et qu'il vient de recevoir une merveille
                    if (player.getHand().size() > 0 && ready == false) {
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
                    if (player.getWonder() != null && ready == false) {
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
        Card card = player.getHand().get(0);

        if (player.canBuild(card)) {
            player.build(card);
            connection.emit("");
        } else {
            player.discard(card);
        }

        connection.emit("playedCard", card.getName());
    }

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        playerName = name;
    }

    public void connect() {
        connection.connect();
    }
}