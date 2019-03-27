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
    private int nbPlayersPlayed = 0;
    private int turnNb = 0;


    public Server(Configuration config) {
        server = new SocketIOServer(config);
        players = new ArrayList<>();

        System.out.println("Server - Preparing listener ...");

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("Server - New client connected : " + socketIOClient.getRemoteAddress());

                // 3 joueurs requis
                if (players.size() < NB_PLAYERS) {
                    Participant p = new Participant(socketIOClient);
                    players.add(p);
                }
            }
        });

        server.addEventListener("identification", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                Participant p = findPlayer(client);

                if (p != null) {
                    p.setname(data);

                    for (Participant pl: players) {
                        pl.getSocket().sendEvent("playerConnected", p.getname());
                    }

                    if (allIdentified()) {
                        startGame();
                    }
                }
            }
        });

        server.addEventListener("playedCard", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                Participant p = findPlayer(client);

                if (p != null) {
                    System.out.println("Server - " + p.getname() + " played " + data);
                    p.removeCard(0);
                    nbPlayersPlayed++;
                }

                if (nbPlayersPlayed == 4 && turnNb < 6) {
                    newTurn();
                }
            }
        });
    }

    private Participant findPlayer(SocketIOClient client) {
        Participant p = null;

        for (Participant pl: players) {
            if (pl.getSocket().equals(client)) {
                p = pl;
                break;
            }
        }

        return p;
    }

    private boolean allIdentified() {
        boolean result = true;

        for (Participant p: players) {
            if (p.getname() == null || p.getname() == "") {
                result = false;
                break;
            }
        }

        return result;
    }

    private void startGame() {
        WonderList wonderlist = new WonderList();
        wonderlist.shuffle();

        //DeckAgeI deckAgeI = new DeckAgeI();
        //deckAgeI.shuffle();
        DeckAgeII deckAgeII = new DeckAgeII();
        deckAgeII.shuffle();
        int cardNumber = 7; // 4 cartes par personne
        for (Participant p: players) {
            ArrayList<String> cardsNames = new ArrayList<>();
            for (int i = 0; i < cardNumber; i++) {

                Card randomCard = deckAgeII.getCard(0);
                deckAgeII.removeCard(0);
                //Card randomCard = deckAgeI.getCard(0);
                //deckAgeI.removeCard(0);

                p.addCard(randomCard);
                cardsNames.add(randomCard.getName());
            }
            JSONArray cards = cardsToJSON(cardsNames);
            p.getSocket().sendEvent("playerCards", cards.toString());
            System.out.println("Server - Sent cards " + cards + " to player " + p.getname());
            Wonder randomWonder = wonderlist.getWonder(0);
            wonderlist.removeWonder(0);
            p.addWonder(randomWonder);
            String wonderName = randomWonder.getName();
            p.getSocket().sendEvent("playerWonder",wonderName);
            System.out.println("Server - Sent wonder " + wonderName + " to player " + p.getname());
        }

        System.out.println("Server - Sending turn event --------------------------------------------------");
        for (Participant p: players) {
            p.getSocket().sendEvent("turn");
        }
        turnNb++;
    }

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
             //   cardName = card.getName();
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
    }

    private JSONArray cardsToJSON(ArrayList<String> cards) {
        JSONArray cardsJ = new JSONArray();
        try {
            for (int i=0;i<cards.size();i++){
                cardsJ.put(cards.get(i));
            }
        }
        catch (Exception e){
            System.out.println("Server - JSON error - " + e.getMessage());
        }
        return cardsJ;
    }

    public void start() {
        server.start();

        System.out.println("Server - Waiting for connection");

        //server.stop();
    }
}
