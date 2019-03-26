package game;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.ArrayList;

public class Participant {

    private String name = "";
    private int score=0;
    public int piece = 0;

    private SocketIOClient socket;
    private Wonder wonder;
    public ArrayList<Card> cards;


    public Participant(SocketIOClient socketIOClient) {
        setSocket(socketIOClient);
        cards = new ArrayList<>();
    }

    public void setSocket(SocketIOClient socket) {
        this.socket = socket;
    }

    public SocketIOClient getSocket() {
        return socket;
    }



    public String toString() {
        return "[Joueur "+getname()+" : "+getSocket().getRemoteAddress()+"]";
    }


    public void setname(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public void setwonder(Wonder wonder) {
        this.wonder = wonder;
    }

    public Wonder getwonder() {
        return wonder;
    }

    public void addWonder(Wonder wonder){wonder.add(wonder);}

    public void addCard(Card card) { cards.add(card); }

    public ArrayList<Card> getCards() { return cards; }

    public void clearCards() { this.cards = new ArrayList<>(); }

    public void removeCard(int i) { if (this.cards.size() > 0) { this.cards.remove(i); } }

    public void addScore(int i){ this.score += i;}

    public int getScore(){ return score;}
}
