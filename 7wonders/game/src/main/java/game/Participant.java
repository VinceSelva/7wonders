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

    public Participant(SocketIOClient socketIOClient) {
        socket = socketIOClient;
        name = "";

        wonder = null;
        buildings = new ArrayList<>();
        hand = new ArrayList<>();
        coins = 3;
        score = 0;
    }

    public HashMap<String, Integer> getProducedResources() {
        HashMap<String, Integer> producedResources = new HashMap<>();

        // Ajoute à la liste des ressources produites par le joueur la ressource produite par la merveille
        producedResources.merge(wonder.getWonderResource().toString(), 1, Integer::sum);

        // Parcourt toutes les constructions afin de compte les ressources produites par les constructions
        for (Card card: buildings) {
            Iterator iterator = card.getProducedResources().entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Integer> pair = (Map.Entry)iterator.next();

                // Si la ressource produite n'est pas une pièce, on l'ajoute à la liste des ressources produites
                if (!pair.getKey().equals("COIN")) {
                    producedResources.merge(pair.getKey(), pair.getValue(), Integer::sum);
                }

                iterator.remove();
            }
        }

        return producedResources;
    }

    public boolean canBuild(Card card) {
        HashMap<String, Integer> producedResources = getProducedResources();

        Cost cardCost = card.getCost();
        HashMap<String, Integer> neededResources = cardCost.getResourcesCost();
        int neededCoins = cardCost.getCoinsCost();

        Iterator iterator = neededResources.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry)iterator.next();

            Integer currentProduced = producedResources.get(pair.getKey());

            if (currentProduced != null) {
                if (currentProduced < pair.getValue()) {
                    return false;
                }
            } else {
                return false;
            }

            iterator.remove();
        }

        if (coins >= neededCoins) {
            return true;
        }

        return false;
    }

    public void build(Card card) {
        int neededCoins = card.getCost().getCoinsCost();

        coins -= neededCoins;

        buildings.add(card);
        hand.remove(card);
    }

    public void discard(Card card) {
        coins += 3;

        hand.remove(card);
    }

    public void play(Card card) {
        if (canBuild(card)) {
            build(card);
        } else {
            discard(card);
        }
    }

    public int bestPlay() {
        for (int i = 0; i < hand.size(); i++) {
            if (canBuild(hand.get(i))) {
                return i;
            }
        }

        return -1;
    }

    public void computeScore() {
        score = 0;

        score += (int)Math.floor(coins / 3);

        for (Card card: buildings) {
            score += card.getPoints();
        }
    }

    public String getScoreExplanations() {
        String scoreStr = "[" + coins + " coins (" + (int)Math.floor(coins / 3) + " points)";

        for (Card card: buildings) {
            int points = card.getPoints();

            if (points > 0) {
                scoreStr += " + ";
                scoreStr += card.getName() + "(" + points + " points)";
            }
        }

        scoreStr += "]";

        return scoreStr;
    }

    public SocketIOClient getSocket() {
        return socket;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWonder(Wonder wonder) {
        this.wonder = wonder;
    }

    public Wonder getWonder() {
        return wonder;
    }

    public void setBuildings(ArrayList<Card> buildings) {
        this.buildings = buildings;
    }

    public void addBuilding(Card card) {
        buildings.add(card);
    }

    public ArrayList<Card> getBuildings() {
        return buildings;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return "[Player " + name + " : " + getSocket().getRemoteAddress() + "]";
    }
}
