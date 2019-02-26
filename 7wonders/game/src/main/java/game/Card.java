package game;


public class Card{

    private CardType type;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Card(CardType type, String name){
        this.type = type;
        this.name= name;
    }

    @Override
    public String toString() {
        return "cardtype: " + type + "cardtype: " + getName() ;
    }

    public boolean equals(Object o) {
        if ((o != null) && (o instanceof Card)) {
            return getName().equals(((Card) o).getName());
        }
        else return false;
    }
}
