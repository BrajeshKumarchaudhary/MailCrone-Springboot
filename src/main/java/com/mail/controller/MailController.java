package com.mail.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailController {
	@Autowired
    private JavaMailSender javaMailSender;
	Logger log=LoggerFactory.getLogger(MailController.class);
@GetMapping("/sendmessage")
public String sendEmail() {

	try {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo("bk04031997@gmail.com","brajesh.kumar@squareyards.co.in");

    msg.setSubject("Testing from Spring Boot");
    msg.setText("Hello World \n Spring Boot Email");

    javaMailSender.send(msg);
	}
	catch(Exception ex)
	{
		log.info("Exception occured",ex);
	}
	return "Message Sent";
}

@RequestMapping("/sendmessagewithattch")
public String sendEmailWithAttachment() throws MessagingException {


    MimeMessage msg = javaMailSender.createMimeMessage();

    // true = multipart message
    MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	
    helper.setTo("to_@email");

    helper.setSubject("Testing from Spring Boot");

    // default = text/plain
    //helper.setText("Check attachment for image!");

    // true = text/html
    helper.setText("<h1>Check attachment for image!</h1>", true);

	// hard coded a file path
    //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

    helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

    javaMailSender.send(msg);
	
	return "Message Sent";
	
}



}
