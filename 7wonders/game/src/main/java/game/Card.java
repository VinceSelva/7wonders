package game;

public class Card{

    private CardType type;
    private String name;
    private ManufacturedGood good;
    private Cost cost;
    private RawMaterials material;
    private RawMaterials ressources[];
    private int value;
    private int pieces;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public int getPieces() { return pieces; }

    public void setPieces(int pieces) { this.pieces = pieces; }

    public CardType getType(){return type;}

    public Card(CardType type, String name) {
        this.type = type;
        this.name = name;
    }

    // type | nom | points de victoire rapportés
    public Card(CardType type, String name, int value) {
        this(type, name);
        this.value = value;
    }

    // type | nom | matière première produite
    public Card(CardType type, String name, RawMaterials material) {
        this(type, name);
        this.material = material;
    }

    // type | nom | produit manufacturé produit
    public Card(CardType type, String name, ManufacturedGood good) {
        this(type, name);
        this.good = good;
    }

    // type | nom | matière première produite | points de victoire rapportés
    public Card(CardType type, String name, RawMaterials material, int value) {
        this(type, name, value);
        this.material = material;
    }

    public Card(CardType type, String name, RawMaterials material, Cost cost) {
        this(type, name, material);
        this.cost = cost;
    }

    public Card(CardType type, String name, int piece, RawMaterials ressourcesProduites[]){
        this(type, name);
        this.pieces = piece;
        this.ressources = ressourcesProduites;
    }

    public Card(CardType type, String name, int piece, RawMaterials ressourcesProduites[], Cost cost){
        this(type, name);
        this.value = piece;
        this.ressources = ressourcesProduites;
        this.cost = cost;
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