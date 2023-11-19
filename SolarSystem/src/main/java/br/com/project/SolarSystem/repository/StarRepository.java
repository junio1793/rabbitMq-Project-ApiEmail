package br.com.project.SolarSystem.repository;

import br.com.project.SolarSystem.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Star,Long> {
}
