package game;

import java.util.List;

public class Wonder {

    private String name;
    private RawMaterials wonderMaterials;
    private List<Card> deck;
    private boolean side;

    public Wonder(String name, RawMaterials wonderMaterials, List<Card> deck) {
        this.name = name;
        this.wonderMaterials = wonderMaterials;
        this.deck = deck;
    }

    public String getwonderMaterials() {
        return wonderMaterials;
    }

    public void setwonderMaterials(String wonderMaterials) {
        this.wonderMaterials = wonderMaterials;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Wonder() { setwonderMaterials("-vide-");}
    public Wonder(String n) { this(); setname(n);}


    public String toString() {
        return "wonder "+getname();
    }


}



