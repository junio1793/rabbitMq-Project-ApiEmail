package br.com.project.SolarSystem.repository;

import br.com.project.SolarSystem.model.Moons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoonRepository extends JpaRepository<Moons,Long> {
}
