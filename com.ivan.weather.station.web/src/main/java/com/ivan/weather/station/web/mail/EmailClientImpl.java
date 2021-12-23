package com.ivan.weather.station.web.mail;

import java.util.Properties;
import java.util.concurrent.ExecutorService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class EmailClientImpl implements EmailClient {

    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMPT_START_TLS_ENABLED = "mail.smtp.starttls.enable";

    private static final String SMTP_HOST_VALUE = "smtp.gmail.com";
    private static final String SMTP_PORT_VALUE = "587";
    private static final String SMTP_AUTH_ENABLED_VALUE = "true";
    private static final String SMPT_START_TLS_ENABLED_VALUE = "true";

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailClientImpl.class);

    private final ExecutorService executorService;

    @Autowired
    public EmailClientImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void sendAsync(Email email) {
        executorService.submit(() -> {
            try {
                Message message = new MimeMessage(getSession());
                message.setFrom(new InternetAddress("automaticmailsendercommunity@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getRecipient()));
                message.setSubject(email.getTitle());
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(email.getContent(), MediaType.TEXT_HTML_VALUE);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                message.setContent(multipart);
                Transport.send(message);
            } catch (MessagingException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
    }

    private Session getSession() {
        return Session.getInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("automaticmailsendercommunity@gmail.com", "buphvgmlikarayke");
            }
        });
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put(MAIL_SMTP_HOST, SMTP_HOST_VALUE);
        properties.put(MAIL_SMTP_PORT, SMTP_PORT_VALUE);
        properties.put(MAIL_SMTP_AUTH, SMTP_AUTH_ENABLED_VALUE);
        properties.put(MAIL_SMPT_START_TLS_ENABLED, SMPT_START_TLS_ENABLED_VALUE);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return properties;
    }
}
