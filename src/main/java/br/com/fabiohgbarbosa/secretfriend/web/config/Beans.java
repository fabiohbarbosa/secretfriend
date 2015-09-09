package br.com.fabiohgbarbosa.secretfriend.web.config;

import com.dumbster.smtp.SimpleSmtpServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create spring beans
 *
 * Created by fabio on 09/09/15.
 */
@Configuration
public class Beans {

//    @Bean
    public SimpleSmtpServer simpleSmtpServer() {
        return new SimpleSmtpServer(1024);
    }
}
