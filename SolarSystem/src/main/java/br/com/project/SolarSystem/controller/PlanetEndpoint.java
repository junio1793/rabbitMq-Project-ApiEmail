package br.com.project.SolarSystem.controller;

import br.com.project.SolarSystem.model.Planets;
import br.com.project.SolarSystem.planetsDto.PlanetCreatedEvent;
import br.com.project.SolarSystem.service.FoundExcepiton;
import br.com.project.SolarSystem.service.PlanetsService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilderSupport;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/planetas")
public class PlanetEndpoint {

    @Autowired
    private  PlanetsService defaultPanetsService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/save")
    public  ResponseEntity<Planets> save(@RequestBody Planets planets) throws FoundExcepiton {
        defaultPanetsService.save(planets);

        PlanetCreatedEvent planetCreatedEvent = new PlanetCreatedEvent(planets.getNomePlanetas(),
                planets.getTamanho());

        rabbitTemplate.convertAndSend("solarSystem.v1","",planetCreatedEvent);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
