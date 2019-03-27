package client;

import game.Card;
import game.DeckAgeI;
import game.Participant;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Client {
    private Socket connection;
    private static String playerName;
    private Participant player = new Participant(null);
    private DeckAgeI deckAgeI = new DeckAgeI();

    public Client(String serverURL, String playerName) {
        try {
            connection = IO.socket(serverURL);

            System.out.println("Client - Connecting");

            connection.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    connection.emit("identification", playerName);
                    setName(playerName);
                }
            });

            connection.on("playerConnected", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String pName = (String)args[0];
                    System.out.println("Client " + playerName + " - Player " + pName + " connected");
                }
            });

            connection.on("playerWonder", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String wonderName = (String)args[0];
                    System.out.println("Client " + playerName + " - received wonder " + wonderName);
                }
            });

            connection.on("playerCards", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        JSONArray cards = new JSONArray((String)args[0]);
                        System.out.println("Client " + playerName + " - received card " + cards);
                        for (int i = 0; i < cards.length(); i++) {
                            player.addCard(deckAgeI.nameToCard(cards.getString(i)));
                        }
                    } catch (Exception e) {
                        System.out.println("Client - JSON error - " + e.getMessage());
                    }


                }
            });

            connection.on("turn", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    int turnNb = (int)args[0];
                    String cardsString = "";
                    ArrayList<Card> playerCards = player.getCards();
                    int playerCardsSize = player.getCards().size();
                    for (Card c: playerCards) {
                        cardsString += " | " + c.getName();
                    }
                    cardsString += " |";
                    System.out.println("Client " + playerName + "piece:+ " + player.piece + " cards :" + cardsString);
                    if ((turnNb % 4) == 0 ){
                        player.clearCards();
                        player.piece+= 1;
                    }
                    else{
                        if(playerCardsSize>1){
                            connection.emit("playedCard", playerCards.get(0).getName());
                            player.clearCards();
                        }
                        else {
                            playerCards.remove(0);
                            System.out.println("fin de la partie");
                        }
                    }
                }
            });

            connection.on("fin de la partie", new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                   
                }
            });


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void setName(String name) { this.playerName = name; }

    public void connect() {
        connection.connect();
    }

}
