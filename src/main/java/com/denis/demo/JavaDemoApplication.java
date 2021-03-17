package com.denis.demo;

import com.denis.demo.WordSource;
import com.denis.demo.RedisGameController;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaDemoApplication {

    /*public static void main(String[] args) {
        SpringApplication.run(JavaDemoApplication.class, args);
    }*/

    public static void main(String[] args){
        RedisGameController gameController = new RedisGameController("127.0.0.1", 6379);
        gameController.createGame("22331s2321");
}
}
