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
        return cards.get(i);
    }

    public void removeCard(int i) {
        cards.remove(i);
    }

    public ArrayList<Card> getCards() { return cards; }
}
