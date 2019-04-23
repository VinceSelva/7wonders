package game;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    /**
     * Construit un nouveau paquet de cartes vide
     */
    Deck() {
        cards = new ArrayList<>();
    }

    /**
     * Ajoute une carte au paquet
     *
     * @param card la carte à ajouter
     */
    void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Mélange le paquet de cartes
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Récupère une carte à une certaine position dans le paquet
     *
     * @param i la position de la carte à récupérer (de 0 à nbCartes-1)
     * @exception RuntimeException si le paquet est vide
     * @return la carte récupérée (Card)
     */
    public Card getCard(int i) {
        if (cards.size() > 0) {
            return cards.get(i);
        } else {
            throw new RuntimeException("The deck is empty");
        }
    }

    /**
     * Supprimer une carte à une certaine position dans le paquet
     *
     * @param i l'index de la carte à supprimer (de 0 à nbCartes-1)
     * @exception RuntimeException si le paquet est vide
     */
    void removeCard(int i) {
        if (cards.size() > 0) {
            cards.remove(i);
        } else {
            throw new RuntimeException("The deck is empty");
        }
    }

    /**
     * Récupère toutes les cartes du paquet
     *
     * @return les cartes du paquet (ArrayList de Card)
     */
    ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Récupère une carte en fonction de son nom
     *
     * @param name le nom de la carte à récupérer
     * @return la carte récupérée (Card)
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