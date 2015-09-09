package br.com.fabiohgbarbosa.secretfriend.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Webapplication using Spring Boot
 * To run application run method main or mvn sprint-boot:run
 *
 * Created by fabio on 08/09/15.
 */
@ComponentScan("br.com.fabiohgbarbosa.secretfriend")
@EntityScan("br.com.fabiohgbarbosa.secretfriend")
@EnableJpaRepositories("br.com.fabiohgbarbosa.secretfriend")
@EnableTransactionManagement
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    public static void main(final String[] args) {
        SpringApplication.run(WebApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }
}