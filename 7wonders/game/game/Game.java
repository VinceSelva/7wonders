package src.main.java.game.game;

import java.util.ArrayList;

public class Game {

    public static final int PlayerMin = 3;
    public static final int PlayerMax = 3;

    private List<Player> players = new ArrayList<>();

    public int turn ;

    public boolean started;

    public Age currentAge ;

    public void start(){

    }

    public boolean isStarted (){
        return started ;
    }

    public Player getRightNeighbor(Player current) {

    }

    public Player getLeftNeighbor(Player current) {

    }

}
