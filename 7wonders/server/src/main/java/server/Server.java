package server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import game.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Server {
    private SocketIOServer server;
    private ArrayList<Participant> players;
    private static final int NB_PLAYERS = 4;
    private static final int CARDS_PER_PLAYER = 7;
    private int turnNb = 0;
    private int nbPlayersPlayed = 0;
    private int nbPlayersReady = 0;
    private WonderList wonderList = new WonderList();
    private DeckAgeI deckAgeI = new DeckAgeI();

    public Server(Configuration config) {
        server = new SocketIOServer(config);
        players = new ArrayList<>();

        System.out.println("Server - Preparing listener ...");

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("Server - New client connected : " + socketIOClient.getRemoteAddress());

                // Tant que tous les joueurs ne se sont pas connectés.
                if (players.size() < NB_PLAYERS) {
                    Participant p = new Participant(socketIOClient);
                    players.add(p);
                }
            }
        });

        server.addEventListener("identification", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest askSender) throws Exception {
                Participant p = findPlayer(client);

                if (p != null) {
                    p.setName(data);

                    if (players.size() == NB_PLAYERS) {
                        if (allIdentified()) {
                            startGame();
                        }
                    }
                }
            }
        });

        server.addEventListener("ready", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest askSender) throws Exception {
                nbPlayersReady++;

                if (nbPlayersReady == NB_PLAYERS) {
                    System.out.println("Server - Sending turn event");

                    for (Participant p: players) {
                        p.getSocket().sendEvent("turn");
                    }

                    turnNb++;
                }
            }
        });

        server.addEventListener("playedCard", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                Participant player = findPlayer(client);
                Card card = deckAgeI.nameToCard(data);

                if (player != null) {
                    System.out.println("Server - " + player.getName() + " played " + data);
                    player.play(card);
                    nbPlayersPlayed++;
                }

                if (nbPlayersPlayed == 4 && turnNb < 6) {
                    //newTurn();
                }
            }
        });
    }

    public void start() {
        server.start();

        System.out.println("Server - Waiting for connection");
    }

    private void startGame() {
        System.out.println("Server - Starting game");

        wonderList.shuffle();
        deckAgeI.shuffle();

        int cardIndex = 0;
        int wonderIndex = 0;
        for (Participant p: players) {
            ArrayList<String> cardsNames = new ArrayList<>();
            ArrayList<Card> playerHand = new ArrayList<>();

            for (int i = 0; i < CARDS_PER_PLAYER; i++) {
                Card card = deckAgeI.getCard(cardIndex);
                cardIndex++;

                playerHand.add(card);
                cardsNames.add(card.getName());
            }
            p.setHand(playerHand);

            JSONArray cards = cardsToJSON(cardsNames);
            p.getSocket().sendEvent("playerCards", cards.toString());

            System.out.println("Server - Sent cards " + cards + " to player " + p.getName());

            Wonder wonder = wonderList.getWonder(wonderIndex);
            wonderIndex++;
            
            p.setWonder(wonder);
            String wonderName = wonder.getName();
            p.getSocket().sendEvent("playerWonder", wonderName);

            System.out.println("Server - Sent wonder " + wonderName + " to player " + p.getName());
        }
    }

    private Participant findPlayer(SocketIOClient client) {
        Participant player = null;

        for (Participant p: players) {
            if (p.getSocket().equals(client)) {
                player = p;
                break;
            }
        }

        return player;
    }

    private boolean allIdentified() {
        for (int i = 0; i < NB_PLAYERS; i++) {
            if (players.get(i).getName().equals("")) {
                return false;
            }
        }

        return true;
    }

    private JSONArray cardsToJSON(ArrayList<String> cards) {
        JSONArray cardsJ = new JSONArray();

        try {
            for (int i=0; i<cards.size(); i++) {
                cardsJ.put(cards.get(i));
            }
        } catch (Exception e) {
            System.out.println("Server - JSON error - " + e.getMessage());
        }

        return cardsJ;
    }
}

/*public class Server {

private void newTurn() {
    ArrayList<Card> firstPlayerCards = players.get(0).getCards();
    ArrayList<String> cardsNames = new ArrayList<>();

    for (int i = 1; i < NB_PLAYERS; i++) {
        Participant p = players.get(i);
        Participant prevP = players.get(i-1);
        ArrayList<Card> pCards = p.getCards();
        prevP.clearCards();


        for (int j = 0; j < pCards.size(); j++) {
            Card card = pCards.get(j);
            cardsNames.add(card.getName());


        }
        JSONArray cards = cardsToJSON(cardsNames);
        prevP.getSocket().sendEvent("playerCards", cards.toString());
        //prevP.getSocket().sendEvent("playerCards", cardName);
        System.out.println("Server - Sent card " + cardsNames + " to player " + prevP.getname());
    }

    Participant lastPlayer = players.get(NB_PLAYERS - 1);
    cardsNames.clear();
    for (int i = 0; i < firstPlayerCards.size(); i++) {
        Card card = firstPlayerCards.get(0);
        cardsNames.add(card.getName());
    }
    JSONArray cards = cardsToJSON(cardsNames);
    lastPlayer.getSocket().sendEvent("playerCards", cards.toString());

    if (lastPlayer.cards.get(0).getType() == CardType.COMMERCIAL_STRUCTURE) {
        lastPlayer.addScore(lastPlayer.cards.get(0).getValue());
    }
    System.out.println("Server - player: " + lastPlayer.getname() + " score: " + lastPlayer.getScore());
    System.out.println("Server - Sent card " + cards + " to player " + lastPlayer.getname());
    nbPlayersPlayed = 0;
    for (Participant p: players) {
        p.getSocket().sendEvent("turn");
    }
    turnNb++;
}*/