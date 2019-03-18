package game;

public class Card{
    
    private CardType type;
    private String name;
    private RawMaterials material;
<<<<<<< HEAD
    private RawMaterials ressources[];
=======
>>>>>>> 4b104cea285d9964d577e8f02e072af0cb6804f7
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

        this.type = type;

        this.value = value;
    }

    public Card(CardType type, String name, RawMaterials material) {
        this.material = material;
    }

<<<<<<< HEAD
    public Card(CardType type, String name, int piece, RawMaterials ressourcesProduites[]){
        this.type = type;
        this.name = name;
        this.value = piece;
        this.ressources = ressourcesProduites;
=======
>>>>>>> 4b104cea285d9964d577e8f02e072af0cb6804f7
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