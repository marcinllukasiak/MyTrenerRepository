package sample.main;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendAttachmentInEmail {
    public static void sendActivateKOD(String kod , String nick, String passwordA , String email) {
        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "MyTrainerProject@gmail.com";

        final String username = "MyTrainerProject";//change accordingly
        final String password = "MyTrainerProjectPassword69";//change accordingly


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Rejestracja konta - aktywacja");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message


            String htmlText = "<h2>Dziekujemy za założenie konta.<h2/>" +
                    "Twój nick to: "+nick +
                    "<br/>Twoje hasło to: "+passwordA+
                    "<br/>KOD AKTYWACYJNY: "+kod+
                    "<h5>W przypadku, gdy nie Pan dokonal rejestracji, proponujemy zapoznać się z produktem, który może Pan znaleść pod linkiem :" +
                    "<br/>lub zignorować tą wiadomość. <h5/>";
            messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
          /*  messageBodyPart = new MimeBodyPart();
            String filename = "/home/manisha/file.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);*/

            // Send the complete message parts
            message.setContent("Loll <br/> franek", "text/html; charset=utf-8");
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}