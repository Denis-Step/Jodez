package com.denis.demo;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class RedisGameController {
    Jedis redis;

    public RedisGameController(String host, int port) {
        this.redis = new Jedis(host, port);
    }

    public void test(){
        this.redis.hset("bb#69", "badaboom", "badaboombadabang");
        String name = this.redis.hget("bb#69", "badaboom");
        System.out.println(name);
    }

    private String opposite(String team){
        if (team == "red"){
            return "blue";
        } else {
            return "red";
        }
    }

    private HashMap<String, String> createBoard(){
        HashMap words = new HashMap<String, String>()
    }

    public HashMap<String, Object> getState (String game_ID){

        if (!(this.redis.exists("state" + game_ID))){
            throw new RuntimeException("Game doesn't exist");
        }

        HashMap
    }
/*
    public HashMap<String, Integer> getScoreState (String gameID){

    }

    public HashMap<String, String> getWordsState (String gameID){

    }

    public void createGame(String gameID){

    }

    public void handleTurn(String gameID, String team, String action, HashMap<String, String> payload ){

    }
    */
}
