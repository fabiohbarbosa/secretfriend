package br.com.fabiohgbarbosa.amigosecreto.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by fabio on 08/09/15.
 */
@ComponentScan("br.com.fabiohgbarbosa.amigosecreto")
@EntityScan("br.com.fabiohgbarbosa.amigosecreto")
@EnableJpaRepositories("br.com.fabiohgbarbosa.amigosecreto")
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }
}