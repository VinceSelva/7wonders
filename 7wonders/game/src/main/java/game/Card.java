package game;

import java.util.HashMap;

public class Card {
    private CardType type;
    private String name;
    private HashMap<String, Integer> producedResources = new HashMap<>();
    private Cost cost = new Cost();
    private int points = 0;

    /**
     * Permet de modéliser une carte avec un type et un nom
     *
     * @param type représente le type de la carte
     * @param name représente le nom de la carte
     */
    Card(CardType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Permet de modéliser une carte avec un type, un nom et une liste de ressources produites
     *
     * @param type représente le type de carte
     * @param name représente le nom de la carte
     * @param producedResources représente les ressources produites par la carte
     */
    Card(CardType type, String name, HashMap<String, Integer> producedResources) {
        this(type, name);
        this.producedResources = producedResources;
    }

    /**
     * Permet de modéliser une carte avec un type, un nom, une liste de ressources produites et un coût
     *
     * @param type représente le type de carte
     * @param name représente le nom de la carte
     * @param producedResources représente les ressources produites par la carte
     * @param cost représente le coût de construction de la carte
     */
    Card(CardType type, String name, HashMap<String, Integer> producedResources, Cost cost) {
        this(type, name, producedResources);
        this.cost = cost;
    }

    /**
     * Permet de modéliser une carte avec un type, un nom et un nombre de points de victoire rapportés par la carte
     *
     * @param type représente le type de carte
     * @param name représente le nom de la carte
     * @param points représente le nombre de points de victoire que la carte rapporte
     */
    Card(CardType type, String name, int points) {
        this(type, name);
        this.points = points;
    }

    /**
     * Permet de modéliser une carte avec un type, un nom, un nombre de points de victoire rapportés et un coût
     *
     * @param type représente le type de carte
     * @param name représente le nom de la carte
     * @param points représente le nombre de points de victoire que la carte rapporte
     * @param cost représente le coût de construction de la carte
     */
    Card(CardType type, String name, int points, Cost cost) {
        this(type, name, points);
        this.cost = cost;
    }

    /**
     * Permet de récupérer le type de la carte
     *
     * @return le type de la carte (CardType)
     */
    public CardType getType() {
        return type;
    }

    /**
     * Permet de récupérer le nom de la carte
     *
     * @return le nom de la carte
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de récupérer la liste de ressources produites par la carte
     *
     * @return les ressources produites par la carte sous forme de HashMap (nom de la ressource en clef et nombre
     *  nécessaire en valeur)
     */
    HashMap<String, Integer> getProducedResources() {
        return producedResources;
    }

    /**
     * Permet de récupérer le coût de la carte
     *
     * @return le coût pour construire la carte (Cost)
     */
    Cost getCost() {
        return cost;
    }

    /**
     * Permet de récupérer le nombre de points de victoire rapportés par la carte
     *
     * @return le nombre de points de victoire que rapporte la carte
     */
    int getPoints() {
        return points;
    }

    /**
     * Permet de comparer deux cartes
     *
     * @param o l'objet à comparer avec l'objet actuel
     * @return true si les objets sont similaires, false sinon
     */
    public boolean equals(Object o) {
        if (o instanceof Card) {
            return name.equals(((Card)o).getName());
        } else {
            return false;
        }
    }

    /**
     * Permet d'obtenir des informations sur la carte
     *
     * @return une chaîne de caractères au format [Card name]
     */
    public String toString() {
        return "[Card " + name + "]";
    }
}