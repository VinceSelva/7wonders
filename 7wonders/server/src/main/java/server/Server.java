package server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import game.Card;
import game.DeckAgeI;
import game.Participant;
import game.Player;
import org.json.JSONArray;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Server {
    private SocketIOServer server;
    private ArrayList<Participant> players;
    private static final int NB_PLAYERS = 3;


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

        // réception de la card jouée
        server.addEventListener("playedCard", Card.class, new DataListener<Card>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Card card, AckRequest ackRequest) throws Exception {
                // retrouver le participant
                Participant p = findPlayer(socketIOClient);
                if (p != null) {
                    System.out.println("serveur > "+p+" a joue "+card);
                    // puis lui supprimer de sa main la card jouée
                    p.getMain().getcards().remove(card);
                    System.out.println("serveur > il reste a "+p+" les cards "+p.getMain().getcards());

                    // etc.
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
            if (p.getname() == null) {
                result = false;
                break;
            }
        }

        return result;
    }

    private void startGame() {
        DeckAgeI deckAgeI = new DeckAgeI();
        deckAgeI.shuffle();

        for (Participant p: players) {
            // 4 cartes par personne
            for (int i = 0; i < 4; i++) {
                Card randomCard = deckAgeI.getCard(0);
                deckAgeI.removeCard(0);

                p.addCard(randomCard);
                String cardName = randomCard.getName();
                p.getSocket().sendEvent("playerCards", cardName);
                System.out.println("Server - Sent card " + cardName + " to player " + p.getname());
            }
        }

    }

    public void start() {
        server.start();

        System.out.println("Server - Waiting for connection");

        //server.stop();
    }
}
