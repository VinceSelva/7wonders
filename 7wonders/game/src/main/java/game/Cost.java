package game;

import java.util.HashMap;

public class Cost {
	private HashMap<String, Integer> resourcesCost;
	private int coinsCost;

	public Cost() {
		this.resourcesCost = new HashMap<>();
		this.coinsCost = 0;
	}

	public Cost(HashMap<String, Integer> resourcesCost) {
		this.resourcesCost = resourcesCost;
		this.coinsCost = 0;
    }

    public Cost(int coins) {
		this.resourcesCost = new HashMap<>();
		this.coinsCost = coins;
	}

	public HashMap<String, Integer> getResourcesCost() {
		return resourcesCost;
	}

	public int getCoinsCost() {
		return coinsCost;
	}
}