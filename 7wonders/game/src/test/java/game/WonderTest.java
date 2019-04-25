package game;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WonderTest {

    @Test
    public void testWonder() {
        Wonder wonder = new Wonder("Test", RawMaterial.WOOD);

        assertEquals(wonder.getName(), "Test");
        assertEquals(wonder.getWonderResource().toString(), "WOOD");
        assertEquals(wonder.toString(), "[Wonder Test, produces WOOD]");
    }
}
