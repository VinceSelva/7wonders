package game;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DeckTest {
    private Deck deck;

    @Test
    public void testAjouterCarte() {
        deck = new Deck();
        Card card = new Card(CardType.RAW_MATERIAL, "CHANTIER", RawMaterials.WOOD);
        deck.addCard(card);
        assertEquals(deck.getCard(0), card, "La carte ajoutée au deck doit se trouver à la première prosition dans le deck");
    }

    @Test
    public void testSupprimerCarte() {
        deck = new Deck();
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", RawMaterials.WOOD));
        deck.removeCard(0);
        assertEquals(deck.getCards().size(), 0, "Il ne devrait plus y avoir de cartes dans le deck");
    }

    @Test
    public void testMelangeDeck() {
        deck = new Deck();
        Card card = new Card(CardType.RAW_MATERIAL, "CHANTIER", RawMaterials.WOOD);
        deck.addCard(card);
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "BASSIN ARGILEUX", RawMaterials.BRICK));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FILON", RawMaterials.ORE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FRICHE", RawMaterials.WOOD));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "EXCAVATION", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FOSSE ARGILEUSE", RawMaterials.BRICK));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "EXPLOITATION FORESTIÈRE", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "GISEMENT", RawMaterials.WOOD));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "MINE", RawMaterials.ORE));
        deck.shuffle();
        assertNotEquals(deck.getCard(0), card);
    }

    @Test
    public void testTailleDeck() {
        deck = new Deck();
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CHANTIER", RawMaterials.WOOD));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "BASSIN ARGILEUX", RawMaterials.BRICK));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FILON", RawMaterials.ORE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FRICHE", RawMaterials.WOOD));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "EXCAVATION", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FOSSE ARGILEUSE", RawMaterials.BRICK));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "EXPLOITATION FORESTIÈRE", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "GISEMENT", RawMaterials.WOOD));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "MINE", RawMaterials.ORE));
        assertEquals(deck.getCards().size(), 10, "Il devrait y avoir 10 cartes dans le deck");
    }

    @Test
    public void testNomVersCarte() {
        deck = new Deck();
        Card card = new Card(CardType.RAW_MATERIAL, "CHANTIER", RawMaterials.WOOD);
        deck.addCard(card);
        deck.addCard(new Card(CardType.RAW_MATERIAL, "CAVITÉ", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "BASSIN ARGILEUX", RawMaterials.BRICK));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FILON", RawMaterials.ORE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FRICHE", RawMaterials.WOOD));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "EXCAVATION", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "FOSSE ARGILEUSE", RawMaterials.BRICK));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "EXPLOITATION FORESTIÈRE", RawMaterials.STONE));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "GISEMENT", RawMaterials.WOOD));
        deck.addCard(new Card(CardType.RAW_MATERIAL, "MINE", RawMaterials.ORE));
        assertEquals(deck.nameToCard("CHANTIER"), card, "On devrait avoir retrouvé la carte CHANTIER dans le deck");
    }
}