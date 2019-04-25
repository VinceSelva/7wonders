package game;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

    @Test
    public void testCard() {
        Card card = new Card(CardType.MANUFACTURED_GOOD, "Test", new HashMap<String, Integer>(){{
            put(RawMaterial.WOOD.toString(), 1);
        }});

        assertEquals(card.getType().toString(), "MANUFACTURED_GOOD");
        assertEquals(card.getName(), "Test");
        assertEquals(card.getProducedResources().toString(), "{WOOD=1}");

        card = new Card(CardType.MANUFACTURED_GOOD, "Test2", 2, new Cost(3));

        assertEquals(card.getCost().getCoinsCost(), 3);
        assertEquals(card.getPoints(), 2);
    }
}
