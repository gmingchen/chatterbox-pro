package com.slipper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author gumingchen
 */
@EnableAsync
@SpringBootApplication
public class SlipperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlipperApplication.class, args);
    }

}
