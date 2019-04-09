package game;

import java.util.HashMap;

public class Card {
    private CardType type;
    private String name;
    private HashMap<String, Integer> producedResources = new HashMap<>();
    private Cost cost = new Cost();
    private int points = 0;

    public Card(CardType type, String name) {
        this.type = type;
        this.name = name;
    }

    public Card(CardType type, String name, HashMap<String, Integer> producedResources) {
        this(type, name);
        this.producedResources = producedResources;
    }

    public Card(CardType type, String name, HashMap<String, Integer> producedResources, Cost cost) {
        this(type, name, producedResources);
        this.cost = cost;
    }

    public Card(CardType type, String name, int points) {
        this(type, name);
        this.points = points;
    }

    public Card(CardType type, String name, int points, Cost cost) {
        this(type, name, points);
        this.cost = cost;
    }

    public CardType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getProducedResources() {
        return producedResources;
    }

    public Cost getCost() {
        return cost;
    }

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