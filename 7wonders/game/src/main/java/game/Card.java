package game;

import java.util.HashMap;

public class Card {
    private CardType type;
    private String name;
    private HashMap<String, Integer> producedResources = new HashMap<>();
    private Cost cost = new Cost();
    private int points = 0;


    /**
     * @param type qui represente le type de carte
     * @param name qui represente le nom de la carte
     */
    public Card(CardType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @param type qui represente le type de carte
     * @param name qui represente le nom de la carte
     * @param producedResources qui represente les ressources produites
     */
    public Card(CardType type, String name, HashMap<String, Integer> producedResources) {
        this(type, name);
        this.producedResources = producedResources;
    }

    /**
     * @param type qui represente le type de carte
     * @param name qui represente le nom de la carte
     * @param producedResources qui represente les ressources produites
     * @param cost qui represente le cout de la carte pour la posée
     */
    public Card(CardType type, String name, HashMap<String, Integer> producedResources, Cost cost) {
        this(type, name, producedResources);
        this.cost = cost;
    }

    /**
     * @param type qui represente le type de carte
     * @param name qui represente le nom de la carte
     * @param points qui represente le nombre de points que la carte rapporte
     */
    public Card(CardType type, String name, int points) {
        this(type, name);
        this.points = points;
    }

    /**
     * @param type qui represente le type de carte
     * @param name qui represente le nom de la carte
     * @param points qui represente le nombre de points que la carte rapporte
     * @param cost qui represente le cout de la carte pour la posée
     */
    public Card(CardType type, String name, int points, Cost cost) {
        this(type, name, points);
        this.cost = cost;
    }

    /**
     * @return type retourne le type de la carte
     */
    public CardType getType() {
        return type;
    }

    /**
     * @return name retourne le nom de la carte
     */
    public String getName() {
        return name;
    }

    /**
     * @return producedResources retourne les ressources produites
     */
    public HashMap<String, Integer> getProducedResources() {
        return producedResources;
    }

    /**
     * @return cost retourne le cout de la carte
     */
    public Cost getCost() {
        return cost;
    }

    /**
     * @return points retourne le nombre de points que rapporte la carte
     */
    public int getPoints() {
        return points;
    }

    public boolean equals(Object o) {
        if ((o != null) && (o instanceof Card)) {
            return name.equals(((Card)o).getName());
        } else {
            return false;
        }
    }

    public String toString() {
        return "[Card " + name + "]";
    }
}