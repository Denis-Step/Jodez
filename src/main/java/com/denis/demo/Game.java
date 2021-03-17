/*package com.denis.demo;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    public HashMap<String, String> wordsState;
    public String winner;
    public String turn;
    public String action;
    public String hint;
    public int attemptsLeft;
    public int redPoints;
    public int bluePoints;
    private ArrayList<String> wordsList;
    private int NUM_WORDS = 5757;
    private int NUM_BLUE_WORDS = 8;
    private int NUM_RED_WORDS = 9;
    private int NUM_BOMB_WORDS = 1;
    private int MAX_ATTEMPTS = 3;



    public Game(String gameID) {
        this.redis = new Jedis("127.0.0.1", "6379");
        this.wordsList = new WordSource("/Users/denisstepanenko/IdeaProjects/demo/src/main/java/com/denis/demo/5lenwords.txt").words;
        System.out.println(this.createBoard());
    }
} */