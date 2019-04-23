package client;

/**
 * Représente un type de client
 *
 * PLAY_FIRST_CARD : le client essayera de joueur tout le temps la première carte de sa main
 * TRY_ONE_CARD : le client essayera de jouer une carte dans sa main
 */
public enum ClientType {
    PLAY_FIRST_CARD,
    TRY_ONE_CARD
}