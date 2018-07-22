package com.wwdlb.hongruan.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    public void sendSimpleMail(String to, String subject, String content) {
       /* SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);*/
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,false);
            sender.send(message);
        } catch (Exception e) {
            System.out.println("发送失败," + e.getMessage());
        }
    }
}
