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

    public Client(String serverURL, String playerName) {
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

    public void connect() {
        connection.connect();
    }

}
