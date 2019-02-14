package src.main.java.game.game;

import java.util.ArrayList;
import client.Client;
import serveur.Serveur;

public class Game {

    public static final int PlayerMin = 3;
    public static final int PlayerMax = 3;

    private List<Player> players = new ArrayList<>();

    public int turn ;

    public boolean started;

    public Age currentAge ;

    public void start(){

    }
/*
    public Player getRightNeighbor(Player current) {

    }

    public Player getLeftNeighbor(Player current) {

    }*/

    public final static void main(String [] args) {

        Thread serveur = new Thread(new Runnable() {
            @Override
            public void run() {
                Serveur.main(null);
            }
        });

        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                Client.main(null);
            }
        });

        serveur.start();
        client.start();

    }

}
