package com.iwcn.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories()
public class PracticaTestingBootApp {

    public static void main(String[] args) {
        SpringApplication.run(PracticaTestingBootApp.class, args);
    }

}
