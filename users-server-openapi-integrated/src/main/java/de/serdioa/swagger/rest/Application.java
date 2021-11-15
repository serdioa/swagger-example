package de.serdioa.swagger.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = { "de.serdioa.swagger.sample.api", "org.openapitools.configuration" }, basePackageClasses = { Application.class })
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
