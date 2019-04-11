package game;

import java.util.ArrayList;
import java.util.Collections;

public class WonderList {
    private ArrayList<Wonder> wonders;

    public WonderList() {
        wonders = new ArrayList<>();
        addWonder(new Wonder("GIZAH", RawMaterial.STONE)) ;
        addWonder(new Wonder("BABYLON", RawMaterial.BRICK));
        addWonder(new Wonder("OLYMPIA", RawMaterial.WOOD));
        addWonder(new Wonder("RHODOS", RawMaterial.ORE));
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

    public ArrayList<Wonder> getwonders() {
        return wonders;
    }

    public Wonder nameToWonder(String name) {
        Wonder wonder = null;

        for (Wonder w: wonders) {
            if (w.getName().equals(name)) {
                wonder = w;
                break;
            }
        }

        return wonder;
    }
}