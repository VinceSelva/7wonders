package game;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WonderListTest {
    private WonderList wonderList;

    @Test
    public void testAddWonder() {
        wonderList = new WonderList();
        Wonder wonder = new Wonder("TEST", RawMaterials.STONE);
        wonderList.addWonder(wonder);
        ArrayList<Wonder> wonders = wonderList.getwonders();
        assertEquals(wonders.get(wonders.size()-1), wonder, "La merveille ajoutée à la liste doit se trouver à la fin de la liste");
    }

    @Test
    public void testShuffle() {
        wonderList = new WonderList();
        String avantShuffle = wonderList.getwonders().toString();
        wonderList.shuffle();
        assertNotEquals(avantShuffle, wonderList.getwonders().toString());
    }

    @Test
    public void testGetWonder() {

    }

    @Test
    public void removeWonder() {
        wonderList = new WonderList();
        int avantRemove = wonderList.getwonders().size();
        wonderList.removeWonder(0);
        assertNotEquals(wonderList.getwonders().size(), avantRemove);
    }
}