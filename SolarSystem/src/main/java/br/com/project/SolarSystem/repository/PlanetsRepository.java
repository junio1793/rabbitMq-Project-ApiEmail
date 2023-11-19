package br.com.project.SolarSystem.repository;

import br.com.project.SolarSystem.model.Planets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetsRepository extends JpaRepository<Planets,Long> {

    public List<Planets> findByNomePlanetasContains(String nomePlanetas);
}
