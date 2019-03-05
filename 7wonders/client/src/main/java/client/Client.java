package client;

import game.Card;
import game.DeckAgeI;
import game.Participant;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
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
                    String cardName = (String)args[0];
                    System.out.println("Client " + playerName + " - received card " + cardName);
                    player.addCard(deckAgeI.nameToCard(cardName));
                }
            });

            connection.on("turn", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String cardsString = "";
                    ArrayList<Card> playerCards = player.getCards();
                    for (Card c: playerCards) {
                        cardsString += " | " + c.getName();
                    }
                    cardsString += " |";
                    System.out.println("Client " + playerName + " - cards :" + cardsString);
                    connection.emit("playedCard", playerCards.get(0).getName());
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
