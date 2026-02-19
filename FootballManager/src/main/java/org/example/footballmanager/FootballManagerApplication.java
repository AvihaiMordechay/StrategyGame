package org.example.footballmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan("org.example.footballmanager.model")
public class FootballManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(FootballManagerApplication.class, args);
    }

}
