package ru.abakumova.appealsapp.services;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.abakumova.appealsapp.services.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("ALL")
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender javaMailSender;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")


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

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo("santosh@example.com");
            mimeMessageHelper.setSubject("Spring Boot=> Sending email with attachment");
            mimeMessageHelper.setText("Dear Santosh, I have sent you Websparrow.org new logo. PFA.");
            mimeMessageHelper.addAttachment("logo.png", new ClassPathResource("logo-100.png"));

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
        }

    }

    @Override
    public void sendHTMLEmail() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo("manish@example.com");
            mimeMessageHelper.setSubject("Spring Boot=> Sending HTML email");

            String html = "<h3>Dear Manish</h3></br>" + "<p>Many many congratulation for joining " + "<strong>Websparrow.org Team</strong>.</p>" + "</br></br>" + "<p>You are entitled for <code>Rs.5000</code> " + "as joning bonus.</p>";
            mimeMessageHelper.setText(html, true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
        }


    }
}
