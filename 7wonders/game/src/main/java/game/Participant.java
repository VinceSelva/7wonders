package game;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Participant {
    private SocketIOClient socket;
    private String name;

    private Wonder wonder;
    private ArrayList<Card> buildings;
    private ArrayList<Card> hand;
    private int coins;
    private int score;

    /**
     * Modélise un joueur
     *
     * @param socketIOClient la socket sur laquelle le client est connecté
     */
    public Participant(SocketIOClient socketIOClient) {
        socket = socketIOClient;
        name = "";

        wonder = null;
        buildings = new ArrayList<>();
        hand = new ArrayList<>();
        coins = 3;
        score = 0;
    }

    /**
     * Calcule et retourne les ressources produites par le joueur
     *
     * @return une HashMap contenant les ressources produites (les clefs correspondent aux noms des ressources et les
     *  valeurs correspondent au nombre produit par ressource)
     */
    private HashMap<String, Integer> getProducedResources() {
        HashMap<String, Integer> producedResources = new HashMap<>();

        // Ajoute à la liste des ressources produites par le joueur la ressource produite par la merveille
        producedResources.merge(wonder.getWonderResource().toString(), 1, Integer::sum);

        // Parcourt toutes les constructions afin de compte les ressources produites par les constructions
        for (Card card: buildings) {
            Iterator iterator = card.getProducedResources().entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry)iterator.next();

                // Si la ressource produite n'est pas une pièce, on l'ajoute à la liste des ressources produites
                if (!pair.getKey().equals("COIN")) {
                    producedResources.merge((String)pair.getKey(), (Integer)pair.getValue(), Integer::sum);
                }

                iterator.remove();
            }
        }

        return producedResources;
    }

    /**
     * Évalue si le joueur peut construire une carte
     *
     * @param card la carte que l'on veut construire
     * @return true si la carte peut être construite, false sinon
     */
    public boolean canBuild(Card card) {
        HashMap<String, Integer> producedResources = getProducedResources();

        Cost cardCost = card.getCost();
        HashMap<String, Integer> neededResources = cardCost.getResourcesCost();
        int neededCoins = cardCost.getCoinsCost();

        Iterator iterator = neededResources.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();

            Integer currentProduced = producedResources.get(pair.getKey());

            if (currentProduced != null) {
                if (currentProduced < (Integer)pair.getValue()) {
                    return false;
                }
            } else {
                return false;
            }

            iterator.remove();
        }

        return coins >= neededCoins;
    }

    /**
     * Construit une carte et supprime la carte de la main du joueur
     *
     * @param card la carte à construire
     */
    public void build(Card card) {
        int neededCoins = card.getCost().getCoinsCost();

        coins -= neededCoins;

        buildings.add(card);
        hand.remove(card);
    }

    /**
     * Défausse une carte et donne 3 pièces au joueur
     *
     * @param card la carte à défausser
     */
    public void discard(Card card) {
        coins += 3;

        hand.remove(card);
    }

    /**
     * Joue une carte (si elle peut être construite elle sera construite, sinon elle sera défaussée)
     *
     * @param card la carte à jouer
     */
    void play(Card card) {
        if (canBuild(card)) {
            build(card);
        } else {
            discard(card);
        }
    }

    /**
     * Teste si le joueur peut construire au moins une carte de sa main
     *
     * @return l'index d'une carte dans la main du joueur si il peut la construire, -1 si il ne peut pas construire
     */
    public int bestPlay() {
        for (int i = 0; i < hand.size(); i++) {
            if (canBuild(hand.get(i))) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Calcule le score du joueur en fonction des pièces et des points de victoire attribués par les cartes construites
     */
    public void computeScore() {
        score = 0;

        score += (int)Math.floor(coins / 3.0);

        for (Card card: buildings) {
            score += card.getPoints();
        }
    }

    /**
     * Récupère le détail du calcul du score
     *
     * @return le détail du calcul du score au format [nombreDePieces coins (nombreDePointsDesPieces points) +
     *  carteConstruite1(nombreDePointsCarte1) + ... + carteConstruiteN(nombreDePointsCarteN)]
     */
    public String getScoreExplanations() {
        StringBuilder scoreStrBuilder = new StringBuilder("[" + coins + " coins (" + (int) Math.floor(coins / 3.0) + " points)");

        for (Card card: buildings) {
            int points = card.getPoints();

            if (points > 0) {
                scoreStrBuilder.append(" + ");
                scoreStrBuilder.append(card.getName()).append("(").append(points).append(" points)");
            }
        }
        scoreStrBuilder.append("]");

        return scoreStrBuilder.toString();
    }

    /**
     * Retourne la socket actuelle sur laquelle le joueur est connecté
     *
     * @return la socket sur laquelle le joueur est connecté (SocketIOClient)
     */
    public SocketIOClient getSocket() {
        return socket;
    }

    /**
     * Modifie le nom du joueur
     *
     * @param name le nouveau nom du joueur
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Récupère le nom du joueur
     *
     * @return le nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie la merveille du joueur
     *
     * @param wonder la nouvelle merveille du joueur
     */
    public void setWonder(Wonder wonder) {
        this.wonder = wonder;
    }

    /**
     * Récupère la merveille du joueur
     *
     * @return la merveille du joueur (Wonder)
     */
    public Wonder getWonder() {
        return wonder;
    }

    /**
     * Modifie la liste des constructions du joueur
     *
     * @param buildings la liste des nouvelles constructions du joueur (ArrayList de Card que le joueur a construites)
     */
    public void setBuildings(ArrayList<Card> buildings) {
        this.buildings = buildings;
    }

    /**
     * Ajoute un bâtiment que le joueur a construit
     *
     * @param card la carte que le joueur a construit
     */
    public void addBuilding(Card card) {
        buildings.add(card);
    }

    /**
     * Récupère la liste des bâtiments que le joueur a construit
     *
     * @return la liste des bâtiments construit par le joueur (ArrayList de Card que le joueur a construites)
     */
    public ArrayList<Card> getBuildings() {
        return buildings;
    }

    /**
     * Modifie la main du joueur
     *
     * @param hand la nouvelle main du joueur (ArrayList de Card présentes dans la main du joueur)
     */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Récupère la main actuelle du joueur
     *
     * @return la liste des cartes présentes dans la main du joueur (ArrayList de Card)
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Modifie le nombre de pièces que possède le joueur
     *
     * @param coins le nouveau nombre de pièces que possède le joueur
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Donne un certain nombre de pièces au joueur
     *
     * @param coins le nombre de pièces à donner au joueur
     */
    public void addCoins(int coins) {
        this.coins += coins;
    }

    /**
     * Récupère le nombre de pièces que possède le joueur
     *
     * @return le nombre de pièces que possède le joueur
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Modifie le score du joueur
     *
     * @param score le nouveau score du joueur
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Ajoute un certain nombre de points de score au joueur
     *
     * @param score le nombre de points de score à ajouter au joueur
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Récupère le score actuel du joueur
     *
     * @return le score actuel du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * Affiche des informations sur le joueur
     *
     * @return les informations sur le joueur au format [Player nomDuJoueur : adresseDeConnexionDuJoueur]
     */
    public String toString() {
        return "[Player " + name + " : " + getSocket().getRemoteAddress() + "]";
    }
}
