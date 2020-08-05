package dao;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {
// 5.1.1 gui toi mail nguoi dung nhap voi token da hash theo chi dinh cua ForgotPassController
    public static boolean sendMail(String mail, String subject, String token) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("thanhluongtrungit@gmail.com", "me.zing.vn123");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("thanhluongtrungit@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject(subject);
            message.setText(token);
            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        sendMail("thnhlngtrung@gmail.com","hihihi","lay lai mat khau");
    }
}
