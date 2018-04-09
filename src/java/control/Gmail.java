/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Edgar
 */
public class Gmail {
    private static final String remitente = "grupolgimport";
    private static final String clave = "lgimport2017";
    public static boolean sendMail(String destinatario, String asunto, String cuerpo){
        boolean rpta = false;
        try {    
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.clave", "miClaveDeGMail");    //La clave de la cuenta
            props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
            props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
            props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
            
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            
            InternetAddress dest = new InternetAddress(destinatario);
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, dest);   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            rpta = true;
        }catch (MessagingException me) {
                me.printStackTrace();   //Si se produce un error
        }
        return rpta;
    } 
}
