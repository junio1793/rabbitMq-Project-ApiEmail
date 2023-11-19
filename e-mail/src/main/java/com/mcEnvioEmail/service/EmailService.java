package com.mcEnvioEmail.service;

import java.time.LocalDateTime;

import com.mcEnvioEmail.DTO.EmailDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mcEnvioEmail.enums.StatusEmail;
import com.mcEnvioEmail.model.EmailModel;
import com.mcEnvioEmail.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private  EmailRepository repository;

	@Autowired
	private  JavaMailSender mailSender;

	@SuppressWarnings("finally")
	public EmailModel sendEmail(EmailDTO emailDTO) {
		EmailModel em = new  EmailModel();
		BeanUtils.copyProperties(emailDTO, em);

		em.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(em.getEmailFrom());
			mailMessage.setTo(em.getEmailTo());
			mailMessage.setSubject(em.getSubject());
			mailMessage.setText(em.getText());
			
			mailSender.send(mailMessage);
			
			em.setStatusEmail(StatusEmail.SENT);

			System.out.println("email enviado: " + em.getStatusEmail().toString());
		} catch (MailException e) {
			e.printStackTrace();
			em.setStatusEmail(StatusEmail.ERROR);
		}finally {
			return repository.save(em);
		}
	}
}
