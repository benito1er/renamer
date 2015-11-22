package com.benito.dalmeida.app.rename;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSender {

	public void sendMail() throws MessagingException {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("84.6.241.95");

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(new String[]{"test@host.com","contact@abbeyservices.fr","benito1er@gmail.com"});
		helper.setText("Thank you for ordering!");
		helper.setFrom("benito1er@free.fr");
		sender.send(message);
	}
}
