package game;

import java.util.HashMap;

public class Cost {
	private HashMap<String, Integer> resourcesCost;
	private int coinsCost;

	public Cost() {
		this.resourcesCost = new HashMap<>();
		this.coinsCost = 0;
	}

	/**
	 * @param resourcesCost represente le cout de la carte pour la posée
	 */
	public Cost(HashMap<String, Integer> resourcesCost) {
		this.resourcesCost = resourcesCost;
		this.coinsCost = 0;
    }
	/**
	 * @param coins represente le cout en coins
	 */
    public Cost(int coins) {
		this.resourcesCost = new HashMap<>();
		this.coinsCost = coins;
	}
	/**
	 * @return resourcesCost retourne le cout en ressources
	 */
	public HashMap<String, Integer> getResourcesCost() {
		return resourcesCost;
	}

	/**
	 * @return coinsCost retourne le cout en coins
	 */
	public int getCoinsCost() {
		return coinsCost;
	}
}