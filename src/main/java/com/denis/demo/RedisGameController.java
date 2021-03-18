package com.denis.demo;
import redis.clients.jedis.Jedis;
import java.util.*;

public class RedisGameController implements GameController {
    private Jedis redis;
    private ArrayList<String> wordsList;
    private final int NUM_BLUE_WORDS = 8;
    private final int NUM_RED_WORDS = 9;
    private final int NUM_BOMB_WORDS = 1;
    private final int MAX_ATTEMPTS = 3;



    public RedisGameController(String host, int port, String path) {
        this.redis = new Jedis(host, port);
        this.wordsList = new WordSource(path).words;
    }

    private String opposite(String team){
        if (team.equals("red")){
            return "blue";
        } else {
            return "red";
        }
    }

    private HashMap<String, String> createBoard(){
        HashMap boardWords = new HashMap<String, String>();

        int numWords = this.wordsList.size();
        Random rand = new Random();
        int redWords = 0;
        int blueWords = 0;
        int bombWords = 0;

        while (boardWords.size() < 25) {
            String newWord = this.wordsList.get(rand.nextInt(numWords));

            if (boardWords.containsKey(newWord)) {
                continue;
            } else if (redWords < NUM_RED_WORDS) {
                boardWords.put(newWord, "red");
                redWords++;
            } else if (blueWords < NUM_BLUE_WORDS) {
                boardWords.put(newWord, "blue");
                blueWords++;
            } else if (bombWords < NUM_BOMB_WORDS) {
                boardWords.put(newWord, "bomb");
                bombWords++;
            } else {
                boardWords.put(newWord, "neutral");
            }
        }

        return boardWords;


        }

    private void spymasterMove(String gameID, String hint, Integer attempts){
        if (attempts > MAX_ATTEMPTS){
            throw new RuntimeException("Cannot ask for more than 3 attempts");
        }

        HashMap<String, String> updateMap = new HashMap<String, String>();
        updateMap.put("hint", hint);
        updateMap.put("attemptsLeft", String.valueOf(attempts));
        updateMap.put("action", "chooser");
        this.redis.hset("state:" + gameID, updateMap);

    }

    /**
         * Modify State normally even if setting a winner to allow for
         * more detailed history.
    **/
    private void chooserMove(String gameID, Map<String, String> wordsMap, String guess, String team){
        if (wordsMap.get(guess).equals("bomb")){
            this.redis.hincrBy("state:" + gameID, "attemptsLeft", -1);
            this.redis.hset("words:" + gameID, guess, "bomb-revealed");
            setWinner(gameID, opposite(team));
        }

        else {

         if (wordsMap.get(guess).equals(team)) {
                this.redis.hincrBy("state:" + gameID, "attemptsLeft", -1);
                this.redis.hincrBy("state:" + gameID, team + "Points", 1);
                this.redis.hset("words:" + gameID, guess, team + "-revealed");
            } else if (wordsMap.get(guess).equals(opposite(team))) {
                this.redis.hset("state:" + gameID, "attemptsLeft", "0");
                this.redis.hincrBy("state:" + gameID, opposite(team) + "Points", 1);
                this.redis.hset("words:" + gameID, guess, opposite(team) + "-revealed");
                this.redis.hset("state:" + gameID, "turn", opposite(team));
                this.redis.hset("state:" + gameID, "action", "spymaster");
            } else if (wordsMap.get(guess).equals("neutral")) {
                this.redis.hset("state:" + gameID, "attemptsLeft", "0");
                this.redis.hset("words:" + gameID, guess, opposite(team) + "-revealed");
                this.redis.hset("state:" + gameID, "turn", opposite(team));
                this.redis.hset("state:" + gameID, "action", "spymaster");
            }

        }
    }

    private void finishTurn(String gameID){
        Map<String, Map<String, String>> state = getState(gameID);
        if (Integer.parseInt(state.get("playerState").get("redPoints")) == NUM_RED_WORDS){
            setWinner(gameID, "red");
        }
        else if (Integer.parseInt(state.get("playerState").get("bluePoints")) == NUM_BLUE_WORDS){
            setWinner(gameID, "blue");
        }

        if (Integer.parseInt(state.get("playerState").get("attemptsLeft")) < 1
                && state.get("playerState").get("action").equals("chooser")){


            HashMap<String, String> updateMap = new HashMap<String, String>();
            updateMap.put("turn", opposite(state.get("playerState").get("turn")));
            updateMap.put("action", "spymaster");

            this.redis.hset("state:" + gameID, updateMap);
        }
    }

    private void setWinner(String gameID, String team){
        this.redis.hset("state:" + gameID, "winner", team);
    }

    public void createGame(String gameID){
        HashMap<String, String> wordsState = this.createBoard();

        if (this.redis.exists("state:" + gameID)){
            throw new RuntimeException("Game Already Exists");
        }

        HashMap<String, String> playerState = new HashMap<String, String>();
        playerState.put("attemptsLeft", "0");
        playerState.put("redPoints", "0");
        playerState.put("bluePoints", "0");
        playerState.put("winner", "none");
        playerState.put("turn", "red");
        playerState.put("action", "spymaster");
        playerState.put("hint", "");
        this.redis.hset("state:" + gameID, playerState);
        this.redis.hset("words:" + gameID, wordsState);
    }


    public Map<String, Map<String, String>> getFullState (String gameID){

        if (!(this.redis.exists("state:" + gameID))){
            throw new RuntimeException("Game doesn't exist");
        }

        HashMap<String, Map<String, String>> fullState = new HashMap<String, Map<String, String>>();
        fullState.put("playerState", this.redis.hgetAll("state:" + gameID));
        fullState.put("wordsState", this.redis.hgetAll("words:" + gameID));

        return fullState;
    }

    public Map<String, Map<String, String>> getState (String gameID){
        HashMap<String, Map<String, String>> fullState = new HashMap<String, Map<String, String>>();
        fullState.put("playerState", this.redis.hgetAll("state:" + gameID));

        Map<String, String> wordsState = this.redis.hgetAll("words:" + gameID);
        for (Map.Entry<String, String> entry: wordsState.entrySet()){
            String val = entry.getValue();
            if (!val.contains("revealed")){
                wordsState.put(entry.getKey(), "hidden");
            }
        }

        fullState.put("wordsState", wordsState);

        return fullState;
    }

    public void handleTurn(String gameID, String team, String action, Map<String, Object> payload ){
       Map<String, Map<String, String>> state = getFullState(gameID);
       Map<String, String> playerState = state.get("playerState");

       if (!playerState.get("winner").equals("none")){
           return;
       }

       if (!playerState.get("turn").equals(team) || !playerState.get("action").equals(action)){
           return;
       }



       if (action.equals("spymaster")){
           // Sloppy type conversion
           int attempts = ((Double) payload.get("attempts")).intValue();
           spymasterMove(gameID, (String) payload.get("hint"), attempts);
       }

       else if (action.equals("chooser")){
           chooserMove(gameID, state.get("wordsState"), (String) payload.get("guess"), playerState.get("turn"));
       }

       finishTurn(gameID);
    }

}
