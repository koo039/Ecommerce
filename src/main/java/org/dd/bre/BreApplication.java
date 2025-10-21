package org.dd.bre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreApplication.class, args);
    }

}
