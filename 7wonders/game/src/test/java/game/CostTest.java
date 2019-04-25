package game;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CostTest {

    @Test
    public void testCost() {
        Cost cost = new Cost(2);

        assertEquals(cost.getResourcesCost().toString(), "{}");
        assertEquals(cost.getCoinsCost(), 2);

        cost = new Cost(new HashMap<String, Integer>(){{
            put(RawMaterial.WOOD.toString(), 1);
        }});

        assertEquals(cost.getResourcesCost().toString(), "{WOOD=1}");
        assertEquals(cost.getCoinsCost(), 0);
    }
}
