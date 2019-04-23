package lanceur;
import client.Client;
import client.ClientType;

import com.corundumstudio.socketio.Configuration;
import server.Server;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Lanceur {
    public static void main(String[] args) {
        int nbPlayer=4;
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Configuration config = new Configuration();
        config.setHostname("127.0.0.1");
        int port = (new Random()).nextInt(10000) + 10000;
        config.setPort(port);

        Server server = new Server(config);
        server.start();

        Client client = new Client("http://127.0.0.1:" + port, "Joueur1", ClientType.PLAY_FIRST_CARD);
        client.connect();

        for (int i=2 ; i<=nbPlayer;i++){
            client = new Client("http://127.0.0.1:" + port, "Joueur"+ i, ClientType.TRY_ONE_CARD);
            client.connect();
        }
    }
}
