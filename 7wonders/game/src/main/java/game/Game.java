package game;

import java.util.ArrayList;
import client.Client;
import server.Server;

public class Game {

    public static final int PlayerMin = 3;
    public static final int PlayerMax = 3;

    private ArrayList<Player> players = new ArrayList<>();

    public int turn ;
    public boolean started;

    public Age currentAge ;

    private ArrayList<Card> cards ;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCartes(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Game() {
        cards = new ArrayList<>();
    }
    public Game(ArrayList<Card> cards) {
        this.cards = cards;
    }


    public void addCards(Card c) {
        getCards().add(c);
    }


    public String toString()  {
        String txt = "[";

        for(Card c : cards) txt += c +" ; ";

        if (txt.length() > 4) txt = txt.substring(0, txt.length()-3);

        txt += "]";
        return txt;
    }

    public static void main(String [] args) {

        Thread serveur = new Thread(() -> Server.main(null));

        Thread client = new Thread(() -> Client.main(null));

        serveur.start();
        client.start();

    }

}
