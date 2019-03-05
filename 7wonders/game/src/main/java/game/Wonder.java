package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wonder extends ArrayList<Wonder> {

    private String name;
    private RawMaterials wonderMaterials;
    private List<Card> deck;
    private boolean side;
   // private ArrayList<Card> cards;

    public Wonder(String name, RawMaterials wonderMaterials/*, List<Card> deck*/) {
        this.name = name;
        this.wonderMaterials = wonderMaterials;
       // this.deck = deck;
    }

    public RawMaterials getWonderMaterials() {
        return wonderMaterials;
    }

    public void setWonderMaterials(RawMaterials wonderMaterials) {
        this.wonderMaterials = wonderMaterials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public Wonder() { setwonderMaterials(RawMaterials);}
  //  public Wonder(String n) { this(); setname(n);}


    public String toString() {
        return "wonder "+ getName();
    }


}



