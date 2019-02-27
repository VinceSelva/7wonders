package game;

import com.corundumstudio.socketio.SocketIOClient;

public class Participant {

    private String name;
    private int score;

    private SocketIOClient socket;
    private Wonder wonder;
    private Main main;


    public Player(SocketIOClient socketIOClient) {
        setSocket(socketIOClient);
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

    public void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }
}
