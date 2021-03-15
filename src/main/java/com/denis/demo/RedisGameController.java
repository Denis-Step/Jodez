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
/*
    public HashMap<String, Integer> getScoreState (String gameID){

    }

    public HashMap<String, String> getWordsState (String gameID){

    }

    public HashMap<String, Object> getState (String gameID){

    }

    public void createGame(String gameID){

    }

    public void handleTurn(String gameID, String team, String action, HashMap<String, String> payload ){

    }
    */
}
