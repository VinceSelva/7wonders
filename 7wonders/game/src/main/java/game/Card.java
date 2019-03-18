package game;

public class Card{
    
    private CardType type;
    private String name;

    private RawMaterials ressources[];
    private Cost cost;
    private int value;
    private int points;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public Card(CardType type, String name) {
        this.type = type;
        this.name = name;
    }

    public Card(CardType type, String name, int points){
        this(type, name);
        this.points = points;
    }

    //Constructeur pour les cartes batiments commerciaux 
    public Card(CardType type, String name, int piece, RawMaterials ressourcesProduites[]){
        this(type, name);
        this.value = piece;
        this.ressources = ressourcesProduites;
    }
  
    //Constructeur pour les cartes matières premieres
    public Card(CardType type, String name, RawMaterials ressourcesProduites[], Cost cost){
        this(type, name);
        this.ressources = ressourcesProduites;
        this.cost = cost;
    }

    //Constructeur pour les cartes produits manufacturés
    public Card(CardType type, String name, Cost cost, int points){
        this(type, name);
        this.cost = cost;
        this.points = points;
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