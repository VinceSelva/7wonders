package server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import game.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Server {
    private SocketIOServer server;
    private ArrayList<Participant> players;
    private static final int NB_PLAYERS = 4;
    private static final int CARDS_PER_PLAYER = 7;
    private int turnNb = 0;
    private int nbPlayersPlayed = 0;
    private int nbPlayersReady = 0;
    private WonderList wonderList = new WonderList();
    private DeckAgeI deckAgeI = new DeckAgeI();

    /**
     * @param config
     */
    public Server(Configuration config) {
        server = new SocketIOServer(config);
        players = new ArrayList<>();

        System.out.println("Server - Preparing listener ...");

        server.addConnectListener(new ConnectListener() {

            /**
             * Cette méthode permet de
             * @param socketIOClient
             */
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("Server - New client connected : " + socketIOClient.getRemoteAddress());

                // Tant que tous les joueurs ne se sont pas connectés.
                if (players.size() < NB_PLAYERS) {
                    Participant p = new Participant(socketIOClient);
                    players.add(p);
                }
            }
        });

        server.addEventListener("identification", String.class, new DataListener<String>() {

            /**
             * @param client
             * @param data
             * @param askSender
             * @throws Exception
             */
            @Override
            public void onData(SocketIOClient client, String data, AckRequest askSender) throws Exception {
                Participant p = findPlayer(client);

                if (p != null) {
                    p.setName(data);

                    if (players.size() == NB_PLAYERS) {
                        if (allIdentified()) {
                            startGame();
                        }
                    }
                }
            }
        });

        server.addEventListener("ready", String.class, new DataListener<String>() {

            /**
             * @param client
             * @param data
             * @param askSender
             * @throws Exception
             */
            @Override
            public void onData(SocketIOClient client, String data, AckRequest askSender) throws Exception {
                nbPlayersReady++;

                if (nbPlayersReady == NB_PLAYERS) {
                    System.out.println("Server - Sending turn event (turn 1)");

                    for (Participant p: players) {
                        p.getSocket().sendEvent("turn");
                    }

                    turnNb++;
                }
            }
        });

        server.addEventListener("build", String.class, new DataListener<String>() {

            /**
             * @param client
             * @param data
             * @param askSender
             * @throws Exception
             */
            @Override
            public void onData(SocketIOClient client, String data, AckRequest askSender) throws Exception {
                Participant player = findPlayer(client);
                Card card = deckAgeI.nameToCard(data);

                if (player != null) {
                    player.build(card);
                    nbPlayersPlayed++;
                    System.out.println("Server - " + player.getName() + " built " + data);
                }

                if (nbPlayersPlayed == NB_PLAYERS && turnNb < 6) {
                    newTurn();
                } else if (nbPlayersPlayed == NB_PLAYERS && turnNb == 6) {
                    endGame();
                }
            }
        });

        server.addEventListener("discard", String.class, new DataListener<String>() {

            /**
             * @param client
             * @param data
             * @param askSender
             * @throws Exception
             */
            @Override
            public void onData(SocketIOClient client, String data, AckRequest askSender)  throws Exception {
                Participant player = findPlayer(client);
                Card card = deckAgeI.nameToCard(data);

                if (player != null) {
                    player.discard(card);
                    nbPlayersPlayed++;
                    System.out.println("Server - " + player.getName() + " discarded " + data);
                }

                if (nbPlayersPlayed == NB_PLAYERS && turnNb < 6) {
                    newTurn();
                } else if (nbPlayersPlayed == NB_PLAYERS && turnNb == 6) {
                    endGame();
                }
            }
        });
    }

    public void start() {
        server.start();

        System.out.println("Server - Waiting for connection");
    }

    /**
     * Ici la méthode permet de lancer le jeu en mélangeant les merveilles et les cartes
     */
    private void startGame() {
        System.out.println("Server - Starting game");

        wonderList.shuffle();
        deckAgeI.shuffle();

        int cardIndex = 0;
        int wonderIndex = 0;
        for (Participant p: players) {
            ArrayList<String> cardsNames = new ArrayList<>();
            ArrayList<Card> playerHand = new ArrayList<>();

            for (int i = 0; i < CARDS_PER_PLAYER; i++) {
                Card card = deckAgeI.getCard(cardIndex);
                cardIndex++;

                playerHand.add(card);
                cardsNames.add(card.getName());
            }
            p.setHand(playerHand);

            JSONArray cards = cardsToJSON(cardsNames);
            p.getSocket().sendEvent("playerCards", cards.toString());

            System.out.println("Server - Sent cards " + cards + " to player " + p.getName());

            Wonder wonder = wonderList.getWonder(wonderIndex);
            wonderIndex++;
            
            p.setWonder(wonder);
            String wonderName = wonder.getName();
            p.getSocket().sendEvent("playerWonder", wonderName);

            System.out.println("Server - Sent wonder " + wonderName + " to player " + p.getName());
        }
    }

    /**
     * Cette méthode permet de créer un nouveau tour de jeu et de le lancer
     */
    private void newTurn() {
        System.out.println("Server - Starting new turn (turn " + (turnNb + 1) + ")");

        ArrayList<Card> firstPlayerCards = players.get(0).getHand();
        ArrayList<String> cardsNames = new ArrayList<>();

        for (int i = 1; i < NB_PLAYERS; i++) {
            Participant p = players.get(i);
            Participant prevP = players.get(i - 1);
            ArrayList<Card> pCards = p.getHand();
            prevP.setHand(pCards);

            for (int j = 0; j < pCards.size(); j++) {
                Card card = pCards.get(j);
                cardsNames.add(card.getName());
            }

            JSONArray cards = cardsToJSON(cardsNames);
            prevP.getSocket().sendEvent("playerCards", cards.toString());

            System.out.println("Server - Sent cards " + cards + " to player " + prevP.getName());

            cardsNames.clear();
        }

        Participant lastPlayer = players.get(NB_PLAYERS - 1);
        cardsNames.clear();
        lastPlayer.setHand(firstPlayerCards);
        for (int i = 0; i < firstPlayerCards.size(); i++) {
            Card card = firstPlayerCards.get(i);
            cardsNames.add(card.getName());
        }
        JSONArray cards = cardsToJSON(cardsNames);
        lastPlayer.getSocket().sendEvent("playerCards", cards.toString());

        System.out.println("Server - Sent cards " + cards + " to player " + lastPlayer.getName());

        nbPlayersPlayed = 0;
        for (Participant p: players) {
            p.getSocket().sendEvent("turn");
        }
        turnNb++;
    }

    /**
     * Cette méthode permet d'envoyer la fin du jeu, d'afficher le score du joueur et d'afficher le meilleur joueur
     * de la partie, donc celui qui gagne la partie
     */
    private void endGame() {
        System.out.println("Server - Game end");

        int bestScore = 0;
        Participant bestPlayer = null;
        for (Participant p: players) {
            p.computeScore();

            System.out.println("Server - " + p.getName() + " scored " + p.getScore());
            System.out.println(p.getScoreExplanations());

            if (p.getScore() > bestScore) {
                bestScore = p.getScore();
                bestPlayer = p;
            }
        }

        System.out.println("Server - " + bestPlayer.getName() + " won the game !");
    }

    /**
     * @param client
     * @return player
     */
    private Participant findPlayer(SocketIOClient client) {
        Participant player = null;

        for (Participant p: players) {
            if (p.getSocket().equals(client)) {
                player = p;
                break;
            }
        }

        return player;
    }


    private boolean allIdentified() {
        for (int i = 0; i < NB_PLAYERS; i++) {
            if (players.get(i).getName().equals("")) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param cards
     * @return cardsJ
     */
    private JSONArray cardsToJSON(ArrayList<String> cards) {
        JSONArray cardsJ = new JSONArray();

        try {
            for (int i=0; i<cards.size(); i++) {
                cardsJ.put(cards.get(i));
            }
        } catch (Exception e) {
            System.out.println("Server - JSON error - " + e.getMessage());
        }

        return cardsJ;
    }
}