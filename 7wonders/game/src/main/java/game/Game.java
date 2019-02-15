package game;

import java.util.ArrayList;
import client.Client;
import server.Server;

public class Game {

    public static final int PlayerMin = 3;
    public static final int PlayerMax = 3;

    private List<Player> players = new ArrayList<>();

    public int turn ;

    public boolean started;

    public Age currentAge ;

    public final static void main(String [] args) {

        Thread serveur = new Thread(() -> Server.main(null));

        Thread client = new Thread(() -> Client.main(null));

        serveur.start();
        client.start();

    }

}
