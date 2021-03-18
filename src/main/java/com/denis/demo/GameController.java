package com.denis.demo;
import java.util.*;

public interface GameController {

    public Map<String, Map<String, String>> getFullState (String gameID);

    public Map<String, Map<String, String>> getState (String gameID);

    public void createGame(String gameID);

    public void handleTurn(String gameID, String team, String action, Map<String, Object> payload );
}
