package ru.abakumova.appealsapp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService  {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendTextEmail(String to, String subject, String text) {

        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(to);
        simpleMessage.setSubject(subject);
        simpleMessage.setText(text);

        javaMailSender.send(simpleMessage);


    }

    @Override
    public void sendEmailWithAttachment() {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {

            // Set multipart mime message true
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                    true);

            mimeMessageHelper.setTo("santosh@example.com");
            mimeMessageHelper
                    .setSubject("Spring Boot=> Sending email with attachment");
            mimeMessageHelper.setText(
                    "Dear Santosh, I have sent you Websparrow.org new logo. PFA.");

            // Attach the attachment
            mimeMessageHelper.addAttachment("logo.png",
                    new ClassPathResource("logo-100.png"));

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
        }

    }

    @Override
    public void sendHTMLEmail() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {

            // Set multipart mime message true
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                    true);

            mimeMessageHelper.setTo("manish@example.com");
            mimeMessageHelper.setSubject("Spring Boot=> Sending HTML email");

            String html = "<h3>Dear Manish</h3></br>"
                    + "<p>Many many congratulation for joining "
                    + "<strong>Websparrow.org Team</strong>.</p>" + "</br></br>"
                    + "<p>You are entitled for <code>Rs.5000</code> "
                    + "as joning bonus.</p>";
            mimeMessageHelper.setText(html, true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
        }


    }
}
