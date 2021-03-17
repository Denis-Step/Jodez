package com.denis.demo;
import redis.clients.jedis.Jedis;
import com.denis.demo.WordSource;
import java.util.*;

public class RedisGameController {
    private Jedis redis;
    private ArrayList<String> wordsList;
    private int NUM_WORDS = 5757;
    private int NUM_BLUE_WORDS = 8;
    private int NUM_RED_WORDS = 9;
    private int NUM_BOMB_WORDS = 1;
    private int MAX_ATTEMPTS = 3;



    public RedisGameController(String host, int port) {
        this.redis = new Jedis(host, port);
        this.wordsList = new WordSource("/Users/denisstepanenko/IdeaProjects/demo/src/main/java/com/denis/demo/5lenwords.txt").words;
    }

    public void test(){
        this.redis.hset("bb#69", "badaboom", "badaboombadabang");
        String name = this.redis.hget("bb#69", "badaboom");
    }

    private String opposite(String team){
        if (team == "red"){
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
            System.out.println(boardWords);
            String newWord = this.wordsList.get(rand.nextInt(numWords));

            if (boardWords.containsKey(newWord)) {
                continue;
            } else if (redWords < NUM_RED_WORDS) {
                System.out.println("hallo");
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
        playerState.put("turn", "blue");
        playerState.put("action", "spymaster");
        this.redis.hset("state:" + gameID, playerState);
        this.redis.hset("words:" + gameID, wordsState);
        Map test = redis.hgetAll("state:" + gameID);
        test = redis.hgetAll("state:" + gameID);

        System.out.println(this.getState(gameID));


    }


    public HashMap<String, Map<String, String>> getState (String gameID){

        if (!(this.redis.exists("state:" + gameID))){
            throw new RuntimeException("Game doesn't exist");
        }

        HashMap<String, Map<String, String>> fullState = new HashMap<String, Map<String, String>>();
        fullState.put("playerState", this.redis.hgetAll("state:" + gameID));
        fullState.put("wordsState", this.redis.hgetAll("words:" + gameID));

        return fullState;
    }

    /*

    public HashMap<String, Integer> getScoreState (String gameID){

    }

    public HashMap<String, String> getWordsState (String gameID){

    }


    public void handleTurn(String gameID, String team, String action, HashMap<String, String> payload ){

    }
*/
}
