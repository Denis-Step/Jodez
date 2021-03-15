package com.denis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaDemoApplication {

    /*public static void main(String[] args) {
        SpringApplication.run(JavaDemoApplication.class, args);
    }*/

    public static void main(String[] args){
        RedisGameController r = new RedisGameController("127.0.0.1", 6379);
        r.test();
    }

}
