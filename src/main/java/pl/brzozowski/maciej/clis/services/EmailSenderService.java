package pl.brzozowski.maciej.clis.services;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Data
public class EmailSenderService {

    private String sender;
    private String title;
    private String message;

    private final String username = "webapi.java@gmail.com";
    private final String password = "webAPIjava1";

    public void sendStandardEmail(String receiver) {
        sendEmail(this.sender, receiver, this.title, this.message);
    }

    private void sendEmail(String sender, String receiver, String title, String massage) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiver));
            message.setSubject(title);
            message.setText(massage);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
