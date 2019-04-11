package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Decktest {
    private Deck deck;

    @Test
    public void testsuffle(){
        deck = new Deck();
        Card card = new Card(CardType.COMMERCIAL_STRUCTURE, "COMPTOIR OUEST");
        deck.addCard(card);
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", new HashMap<String, Integer>(){{
            put(RawMaterial.WOOD.toString(), 1);
        }}));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", new HashMap<String, Integer>(){{
            put(RawMaterial.STONE.toString(), 1);
        }}));
        deck.addCard(new Card(CardType.MANUFACTURED_GOOD, "MÉTIER À TISSER", new HashMap<String, Integer>(){{
            put(ManufacturedGood.TEXTILES.toString(), 1);
        }}));
        deck.addCard(new Card(CardType.MANUFACTURED_GOOD, "VERRERIE", new HashMap<String, Integer>(){{
            put(ManufacturedGood.GLASS.toString(), 1);
        }}));
        deck.addCard(new Card(CardType.CIVILIAN_STRUCTURE, "PRÊTEUR SUR GAGES", 3));
        deck.addCard(new Card(CardType.CIVILIAN_STRUCTURE, "BAINS", 3, new Cost(3)));
        deck.addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "TAVERNE", new HashMap<String, Integer>(){{
            put("COIN", 5);
        }}));
        deck.addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "COMPTOIR EST"));
        deck.addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "MARCHE"));
        deck.shuffle();
        assertNotEquals(deck.getCard(0), card);
    }
    
    @Test
    public void testremoveCard(){
        deck = new Deck();
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", new HashMap<String, Integer>(){{
            put(RawMaterial.WOOD.toString(), 1);
        }}));
        deck.removeCard(0);
        assertEquals(deck.getCards().size(), 0, "Il ne devrait plus y avoir de cartes dans le deck");
    }

    @Test
    public void testnametocard(){
        deck = new Deck();
        Card card = new Card(CardType.RAW_MATERIAL, "CHANTIER", new HashMap<String, Integer>(){{
            put(RawMaterial.WOOD.toString(), 1);
        }});
        deck.addCard(card);
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", new HashMap<String, Integer>(){{
            put(RawMaterial.STONE.toString(), 1);
        }}));
        deck.addCard(new Card(CardType.MANUFACTURED_GOOD, "MÉTIER À TISSER", new HashMap<String, Integer>(){{
            put(ManufacturedGood.TEXTILES.toString(), 1);
        }}));
        deck.addCard(new Card(CardType.MANUFACTURED_GOOD, "VERRERIE", new HashMap<String, Integer>(){{
            put(ManufacturedGood.GLASS.toString(), 1);
        }}));
        deck.addCard(new Card(CardType.CIVILIAN_STRUCTURE, "PRÊTEUR SUR GAGES", 3));
        deck.addCard(new Card(CardType.CIVILIAN_STRUCTURE, "BAINS", 3, new Cost(3)));
        deck.addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "TAVERNE", new HashMap<String, Integer>(){{
            put("COIN", 5);
        }}));
        deck.addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "COMPTOIR EST"));
        deck.addCard(new Card(CardType.COMMERCIAL_STRUCTURE, "MARCHE"));
        assertEquals(deck.nameToCard("CHANTIER"), card, "On devrait avoir retrouvé la carte CHANTIER dans le deck");
        }

}