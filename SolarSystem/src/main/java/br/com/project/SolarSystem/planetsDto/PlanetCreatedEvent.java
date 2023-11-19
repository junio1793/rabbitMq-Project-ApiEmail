package br.com.project.SolarSystem.planetsDto;

import jakarta.persistence.Column;

public class PlanetCreatedEvent {

    private String nomePlanetas;
    private double tamanho;

    public String getNomePlanetas() {
        return nomePlanetas;
    }

    public double getTamanho() {
        return tamanho;
    }

    public PlanetCreatedEvent() {
    }

    public PlanetCreatedEvent(String nomePlanetas, double tamanho) {
        this.nomePlanetas = nomePlanetas;
        this.tamanho = tamanho;
    }
}
