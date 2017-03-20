/**
 * Created by Dionis on 05/02/2016.
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Abans de fer servir l'aplicació heu de permetre l'autenticació poc segura des de GMAIL.
 * Per això heu d'iniciar el navegador amb la sessió, i després posar aquest enllaç.
 * https://www.google.com/settings/security/lesssecureapps
 */

public class Cutremail2 {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    public static void main(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail();
        System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
    }

    public static void generateAndSendEmail() throws AddressException, MessagingException {

        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");//puerto a usar
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);//genera el mensaje
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("y2793623b@iespoblenou.org"));//cuenta a gmail enviar
        generateMailMessage.setSubject("Nova conta per a proves");//titulo
        String emailBody = "Això és el futur spam! " + "<br><br> Records!, <br>Dionis";//mensaje a enviar
        generateMailMessage.setContent(emailBody, "text/html");//estilo de fitxero (sino se pone lo envia en texto plano)
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");//Tipo de protocolo en el que se enviara

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com","dremon.iespoblenou@gmail.com","mirandolamon");// SERVIDOR , tu cuenta, password de la cuenta
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());//enviar el mensaje
        transport.close();//la cerramos
    }
}
