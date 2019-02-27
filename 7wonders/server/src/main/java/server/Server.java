package server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import game.DeckAgeI;
import game.Participant;
import game.Player;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Server {
    private SocketIOServer server;
    private ArrayList<Participant> players;
    private static final int NB_PLAYERS = 3;


    public Server(Configuration config) {
        server = new SocketIOServer(config);

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
                p.addCard(deckAgeI.getCard(0));
                deckAgeI.removeCard(0);
            }

            // Envoie des cartes au joueur
            p.getSocket().sendEvent("playerCards", p.getCards().get(0));
        }
    }

    private void start() {
        server.start();

        System.out.println("Server - Waiting for connection");

        server.stop();
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();
        config.setHostname("127.0.0.1");
        config.setPort(12345);

        Server server = new Server(config);
        server.start();

        System.out.println("Server - End of server main");
    }
}