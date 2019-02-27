package game;

import java.util.ArrayList;

public class Main {

    private ArrayList<Card> cards;

    public ArrayList<Card> getcards() {
        return cards;
    }

    public void setcards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Main() {
        cards = new ArrayList<>();
    }

    public Main(ArrayList<Card> cards) {
        this.cards = cards;
    }


    public void addCard(Card c) {
        getcards().add(c);
    }


    public String toString() {
        String texte = "[";

        for (Card c : cards) texte += c + " ; ";

        if (texte.length() > 4) texte = texte.substring(0, texte.length() - 3);

        texte += "]";
        return texte;
    }
}
