package com.cythr.greenhouseapi;

import com.cythr.greenhouseapi.annotations.Generated;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * ServletInitializer class
 */
@Generated
@SuppressWarnings("PMD")
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GreenhouseApiApplication.class);
    }

}
