package game;

public class Card{
    
    private CardType type;
    private String name;
    private RawMaterials material;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public CardType getType(){return type;}

    public Card(CardType type, String name, int value){
        this.type = type;
        this.name= name;
        this.value = value;
    }

    public Card(CardType type, String name, RawMaterials material) {
        this.type = type;
        this.name = name;
        this.material = material;
    }

    @Override
    public String toString() {
        return name + ", type : " + type + ", valeur : " + value;
    }

    public boolean equals(Object o) {
        if ((o != null) && (o instanceof Card)) {
            return getName().equals(((Card) o).getName());
        }
        else return false;
    }
}