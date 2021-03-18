package com.denis.demo;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;


public class JavaDemoApplication {

    public static void main(String[] args){

        RedisGameController gc = new RedisGameController("127.0.0.1", 6379, "/Users/denisstepanenko/IdeaProjects/demo/src/main/java/com/denis/demo/5lenwords.txt");
        staticFiles.location("/client/static/");
        port(5000);

        get("/games/:gameID", (req, resp) -> {
            String gameID = req.params(":gameID");
            Map<String, Map<String, String>> stateMap = gc.getState(gameID);
            resp.type("application/json");

            String stateJSON = new Gson().toJson(stateMap);
            return stateJSON;
        });

        get("/games/:gameID/spymaster", (req, resp) -> {
            String gameID = req.params(":gameID");
            Map<String, Map<String, String>> stateMap = gc.getFullState(gameID);
            for (Map.Entry<String, String> entry: stateMap.get("wordsState").entrySet()){
                String val = entry.getValue();
                if (!val.contains("revealed")){
                    stateMap.get("wordsState").put(entry.getKey(), val + "-revealed");
                }
            }


            resp.type("application/json");

            String stateJSON = new Gson().toJson(stateMap);
            return stateJSON;
        });

        post("/games/", (req, resp) -> {
            HashMap<String, Object> jsonMap = new Gson().fromJson(req.body(), HashMap.class);
            String gameID = (String) jsonMap.get("gameID");
            gc.createGame(gameID);


            resp.status(201);
            resp.type("application/json");
            return gc.getState(gameID);
        } );

        post("/games/:gameID", (req, resp) -> {
            HashMap<String, Object> jsonMap = new <String, String> Gson().fromJson(req.body(), HashMap.class);
            String gameID = req.params(":gameID");

            gc.handleTurn(gameID, (String) jsonMap.get("team"), (String) jsonMap.get("action"), (Map<String, Object>) jsonMap.get("payload"));
            return 201;

        });

        get("/",(req, res) -> {
            res.redirect("/index.html");
            return null;
        } );
}
}
