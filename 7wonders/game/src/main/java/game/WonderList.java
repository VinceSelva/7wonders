package game;

import java.util.ArrayList;
import java.util.Collections;

public class WonderList {
    private ArrayList<Wonder> wonders;

    /**
     * Modélise une liste des différentes merveilles
     */
    public WonderList() {
        wonders = new ArrayList<>();
        addWonder(new Wonder("GIZAH", RawMaterial.STONE)) ;
        addWonder(new Wonder("BABYLON", RawMaterial.BRICK));
        addWonder(new Wonder("OLYMPIA", RawMaterial.WOOD));
        addWonder(new Wonder("RHODOS", RawMaterial.ORE));
    }

    /**
     * Ajoute une merveille à la liste
     *
     * @param wonder la merveille à ajouter
     */
    void addWonder(Wonder wonder) {
        wonders.add(wonder);
    }

    /**
     * Mélange la liste des merveilles
     */
    public void shuffle() {
        Collections.shuffle(wonders);
    }

    /**
     * Récupère une merveille dans la liste
     *
     * @param i la position de la merveille dans la liste (0 à nombreMerveilles-1)
     * @return la merveille récupérée (Wonder)
     */
    public Wonder getWonder(int i) {
        return wonders.get(i);
    }

    /**
     * Supprime une merveille de la liste
     *
     * @param i la position de la merveille dans la liste (0 à nombreMerveilles-1)
     */
    void removeWonder(int i) {
        wonders.remove(i);
    }

    /**
     * Récupère la liste des merveilles
     *
     * @return la liste des merveilles (ArrayList de Wonder)
     */
    ArrayList<Wonder> getwonders() {
        return wonders;
    }

    /**
     * Récupère une merveille en fonction de son nom
     *
     * @param name le nom de la merveille à récupérer
     * @return la merveille récupérée (Wonder)
     */
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