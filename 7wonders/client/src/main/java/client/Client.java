package client;

import game.Card;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Client {
    private Socket connection;

    private final Object waitingDisconnection = new Object();

    private Client(String serverURL, String playerName) {
        try {
            connection = IO.socket(serverURL);

            System.out.println("Client - Connecting");

            connection.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    connection.emit("identification", playerName);
                }
            });

            connection.on("playerConnected", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String pName = (String)args[0];
                    System.out.println("Player " + pName + " connected");
                }
            });

            connection.on("playerCards", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Card firstCard = (Card)args[0];
                    System.out.println("My cards : " + firstCard);
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        connection.connect();

        System.out.println("Client - Waiting for disconnection");

        synchronized (waitingDisconnection) {
            try {
                waitingDisconnection.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Client - Error during wait");
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Client client = new Client("http://127.0.0.1:12345", "Joueur 1");
        client.connect();

        System.out.println("Client - End of main");
    }
}
