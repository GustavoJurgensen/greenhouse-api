package com.cythr.greenhouseapi;

import com.cythr.greenhouseapi.annotations.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Start Application
 */
@SuppressWarnings("PMD.UseUtilityClass")
@SpringBootApplication
public class GreenhouseApiApplication {
    /**
     * Start Application
     * @param args
     */
    @Generated
    public static void main(final String[] args) {
        SpringApplication.run(GreenhouseApiApplication.class, args);
    }

}
