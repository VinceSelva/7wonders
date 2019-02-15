package server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Server {
    SocketIOServer server;
    final Object waitingConnection = new Object();

    public Server(Configuration config) {
        server = new SocketIOServer(config);

        System.out.println("Server - Preparing listener ...");

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("Server - New client connected : " + socketIOClient.getRemoteAddress());

                socketIOClient.sendEvent("disconnect");

                synchronized (waitingConnection) {
                    waitingConnection.notify();
                }
            }
        });
    }

    private void start() {
        server.start();

        System.out.println("Server - Waiting for connection");

        synchronized (waitingConnection) {
            try {
                waitingConnection.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.err.println("Server - Error during wait");
            }
        }

        System.out.println("Server - New connection, stopping server.");
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