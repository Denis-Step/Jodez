package com.denis.demo;
import java.util.HashMap;

public interface GameController {

    // Modify to Allow Nested Hashmap
    public HashMap<String, Integer> getScoreState (String gameID);

    public HashMap<String, String> getWordsState (String gameID);

    public HashMap<String, Object> getState (String gameID);

    public void createGame(String gameID);

    public void handleTurn(String gameID, String team, String action, HashMap<String, String> payload );
}
