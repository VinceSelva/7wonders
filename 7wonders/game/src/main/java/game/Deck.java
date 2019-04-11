package game;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @param i qui represente l'index de la carte
     * @return cards.get(1) retourne la carte a recuperer avec son index i
     */
    public Card getCard(int i) {
        if (cards.size() > 0) {
            return cards.get(i);
        }
        return null;
    }

    /**
     * @param i qui represente l'index de la carte
     * @return cards.remove(1) retourne la carte a enlever avec son index i
     */
    public void removeCard(int i) {
        if (cards.size() > 0) {
            cards.remove(i);
        }
    }

    /**
     * @return cards retourne les cartes de l'arraylist
     *
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * @param name qui represente le nom de la carte
     */
    public Card nameToCard(String name) {
        Card card = null;

        for (Card c: cards) {
            if (c.getName().equals(name)) {
                card = c;
                break;
            }
        }

        return card;
    }
}