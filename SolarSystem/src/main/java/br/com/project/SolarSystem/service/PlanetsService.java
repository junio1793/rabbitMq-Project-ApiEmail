package br.com.project.SolarSystem.service;

import br.com.project.SolarSystem.model.Planets;
import br.com.project.SolarSystem.model.Star;
import br.com.project.SolarSystem.repository.PlanetsRepository;
import br.com.project.SolarSystem.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanetsService {

    @Autowired
    private PlanetsRepository planetsRepository;


    @Autowired
    private StarRepository starRepository;

    public PlanetsService() {
    }

    @Transactional
    public Planets getMaiorPlaneta() {
        List<Planets> planetas = planetsRepository.findAll();
        return planetas.stream()
                .max(Comparator.comparingDouble(Planets::getTamanho))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "algo de errado aconteceu"));
    }

    @Transactional
    public Planets getMenorPlaneta() {
        List<Planets> planetas = planetsRepository.findAll();
        return planetas.stream()
                .min(Comparator.comparingDouble(Planets::getTamanho))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "algo de errado aconteceu"));
    }

    @Transactional
    public List<Planets> maiorQueTerra() {
        List<Planets> planetas = planetsRepository.findAll();
        return planetas.stream()
                .sorted(Comparator.comparing(Planets::getNomePlanetas))
                .filter(planets -> planets.getTamanho() < 12756.2)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long quantosPlanetas() {
        List<Planets> planetas = planetsRepository.findAll();
        if (planetas.isEmpty()) {
            return null;
        } else {
            Long count = planetas.stream().filter(planets -> planets.getId() > 0).count();
            return count;
        }
    }

    public Planets findId(Long id) {
        Optional<Planets> objetoRecebido = planetsRepository.findById(id);
        return objetoRecebido
                .orElseThrow(() -> new RuntimeException("planeta não encontrado: " + Planets.class.getName()));
    }

    public Planets save(Planets planets) throws FoundExcepiton {
        Star star = planets.getStar();
        if (star.getId() == null) {
            starRepository.save(star);
            planetsRepository.save(planets);
        }
        return planetsRepository.save(planets);
    }

    public List<Planets> findByName(String name) {
        List<Planets> planets = planetsRepository.findByNomePlanetasContains(name);
        return planets;
    }

    @Transactional
    public List<Planets> findAll() {
        List<Planets> planets = planetsRepository.findAll();
        return planets;
    }
}
