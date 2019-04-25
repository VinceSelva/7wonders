package game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WonderListTest {
    private WonderList wonderList;

    @Test
    public void testAddWonder() {
        wonderList = new WonderList();
        Wonder wonder = new Wonder("GIZAH", RawMaterial.STONE) ;
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
    public void testRemoveWonder() {
        wonderList = new WonderList();
        int avantRemove = wonderList.getwonders().size();
        wonderList.removeWonder(0);
        assertNotEquals(wonderList.getwonders().size(), avantRemove);
    }

    @Test
    public void testNameToWonder() {
        wonderList = new WonderList();
        Wonder wonder = new Wonder("GIZAH", RawMaterial.STONE) ;
        wonderList.addWonder(wonder);
        wonderList.addWonder(new Wonder("OLYMPIA", RawMaterial.WOOD));
        wonderList.addWonder(new Wonder("RHODOS", RawMaterial.ORE));
        assertEquals(wonderList.nameToWonder("GIZAH").toString(), wonder.toString(), "On devrait avoir retrouvé la wonder GIZAH");
    }

    @Test
    public void testGetWonder() {
        wonderList = new WonderList();
        assertEquals(wonderList.getWonder(0).toString(), "[Wonder GIZAH, produces STONE]");
    }

    @Test
    public void testGetWonders() {
        wonderList = new WonderList();
        assertEquals(wonderList.getwonders().toString(), "[[Wonder GIZAH, produces STONE], [Wonder BABYLON, produces BRICK], [Wonder OLYMPIA, produces WOOD], [Wonder RHODOS, produces ORE]]");
    }
}