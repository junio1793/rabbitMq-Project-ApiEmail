package com.mcEnvioEmail.listener;

import com.mcEnvioEmail.DTO.EmailDTO;
import com.mcEnvioEmail.controller.EmailRest;
import com.mcEnvioEmail.planetsDto.PlanetCreatedEvent;
import com.mcEnvioEmail.service.EmailService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListenerMessage {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "solarSystem.v1.created")
    public void onMessage(PlanetCreatedEvent event){
        System.out.println("nome: " + event.getNomePlanetas());
        System.out.println("tamanho: " + event.getTamanho());

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setOwnerRef("junior");
        emailDTO.setEmailFrom("sjunior863@gmail.com");
        emailDTO.setEmailTo("junior.rivaldodos.santos@escola.pr.gov.br");
        emailDTO.setSubject("teste");
        emailDTO.setText("criado novo planeta: " + event.getNomePlanetas() + "\ntamanho: " + event.getTamanho());

        emailService.sendEmail(emailDTO);
    }

}
