package game;

import java.util.HashMap;

public class Cost {
	private HashMap<String, Integer> resourcesCost;
	private int coinsCost;

	/**
	 * Représente un coût en ressources et en pièces
	 */
	Cost() {
		this.resourcesCost = new HashMap<>();
		this.coinsCost = 0;
	}

	/**
	 * Permet de créer un coût en ressources
	 *
	 * @param resourcesCost le coût à modéliser avec une HashMap de ressources (nom de la ressource en clef et nombre
	 *  nécessaire en valeur)
	 */
	public Cost(HashMap<String, Integer> resourcesCost) {
		this.resourcesCost = resourcesCost;
		this.coinsCost = 0;
    }
	/**
	 * Permet de créer un coût en pièces
	 *
	 * @param coins le coût à modéliser en pièces
	 */
	Cost(int coins) {
		this.resourcesCost = new HashMap<>();
		this.coinsCost = coins;
	}

	/**
	 * Retourne un coût en ressources
	 *
	 * @return une HashMap contenant en clef le nom de la ressource et en valeur le nombre nécessaire
	 */
	HashMap<String, Integer> getResourcesCost() {
		return resourcesCost;
	}

	/**
	 * Retourne un coût en pièces
	 *
	 * @return 0 (si aucun coût) ou le coût en pièces
	 */
	int getCoinsCost() {
		return coinsCost;
	}
}