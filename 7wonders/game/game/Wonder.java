package src.main.java.game.game;

import java.util.List;

public class Wonder {

    private String name;
    private RawMaterials wonderMaterials;
    private List<Card> deck;

    //private Side side;

    public Wonder(String name, RawMaterials wonderMaterials, List<Card> deck) {
        this.name = name;
        this.wonderMaterials = wonderMaterials;
        this.deck = deck;
    }

}



