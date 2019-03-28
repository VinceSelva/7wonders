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

    public Card getCard(int i) {
        if (cards.size() > 0) {
            return cards.get(i);
        }
        return null;
    }

    public void removeCard(int i) {
        if (cards.size() > 0) {
            cards.remove(i);
        }
    }

    public ArrayList<Card> getCards() { return cards; }

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
