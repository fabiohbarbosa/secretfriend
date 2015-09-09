package br.com.fabiohgbarbosa.secretfriend.smtpserver;

import com.dumbster.smtp.SimpleSmtpServer;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * Fake SMTP Server
 * Created by fabio on 09/09/15.
 */
@Service
public class SmtpServer {

    private static final int PORT = 1024;

    private SimpleSmtpServer smtpServer = new SimpleSmtpServer(PORT);

    public void startServer() {
        SimpleSmtpServer.start(PORT);
    }

    public void sendEmail(final String to, final String subject, final String body) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("localhost");
        email.setSmtpPort(PORT);
        email.setFrom("sortition@secrectfriend.com");
        email.addTo(to);
        email.setSubject(subject);
        email.setMsg(body);
        email.send();
    }
}
