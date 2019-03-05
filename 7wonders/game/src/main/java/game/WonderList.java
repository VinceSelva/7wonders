package game;

import java.util.ArrayList;
import java.util.Collections;

public class WonderList {
    private ArrayList<Wonder> wonders;

    public WonderList() {
        wonders = new ArrayList<>();
        addWonder(new Wonder("GIZAH", RawMaterials.STONE)) ;
        addWonder(new Wonder("BABYLON", RawMaterials.BRICK));
        addWonder(new Wonder("OLYMPIA", RawMaterials.WOOD));
        addWonder(new Wonder("RHODOS", RawMaterials.ORE));
        //addWonder(new Wonder("EPHESOS", RawMaterials.BRICK));
        //addWonder(new Wonder("ALEXANDRIA", RawMaterials.BRICK));
        //addWonder(new Wonder("HALIKARNASSOS", RawMaterials.BRICK));
    }

    public void addWonder(Wonder wonder) {
        wonders.add(wonder);
    }

    public void shuffle() {
        Collections.shuffle(wonders);
    }

    public Wonder getWonder(int i) {
        return wonders.get(i);
    }

    public void removeWonder(int i) {
        wonders.remove(i);
    }

    public ArrayList<Wonder> getwonders() { return wonders; }

    //addWonder(new Wonder("GIZEH", RawMaterials.WOOD)) ;
}
