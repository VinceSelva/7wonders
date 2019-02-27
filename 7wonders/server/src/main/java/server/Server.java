package server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import game.Player;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Server {
    private SocketIOServer server;
    private ArrayList<Player> players;

    public Server(Configuration config) {
        server = new SocketIOServer(config);

        System.out.println("Server - Preparing listener ...");

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("Server - New client connected : " + socketIOClient.getRemoteAddress());

                // 3 joueurs requis
                if (players.size() < 3) {
                    Player p = new Player(socketIOClient);
                    players.add(p);
                }
            }
        });

        server.addEventListener("identification", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                Player p = findPlayer(client);

                if (p != null) {
                    p.setname(data);

                    for (Player pl: players) {
                        pl.getSocket().sendEvent("playerConnected", p.getname());
                    }

                    if (allIdentified()) {
                        startGame();
                    }
                }
            }
        });
    }

    private Player findPlayer(SocketIOClient client) {
        Player p = null;

        for (Player pl: players) {
            if (pl.getSocket().equals(client)) {
                p = pl;
                break;
            }
        }

        return p;
    }

    private boolean allIdentified() {
        boolean result = true;

        for (Player p: players) {
            if (p.getname() == null) {
                result = false;
                break;
            }
        }

        return result;
    }

    private void startGame() {
        DeckAgeI deckAgeI = new DeckAgeI();
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