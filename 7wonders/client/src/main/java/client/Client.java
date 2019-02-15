package client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class Client {
    private Socket connection;

    private final Object waitingDisconnection = new Object();

    private Client(String serverURL) {
        try {
            connection = IO.socket(serverURL);

            System.out.println("Client - Connecting");

            connection.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Client - Connected");
                }
            });

            connection.on("disconnect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("Client - Disconnected");

                    connection.disconnect();
                    connection.close();

                    synchronized (waitingDisconnection) {
                        waitingDisconnection.notify();
                    }
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

        Client client = new Client("http://127.0.0.1:12345");
        client.connect();

        System.out.println("Client - End of main");
    }
}
