package br.com.project.SolarSystem.service;

public class FoundExcepiton extends Exception {

    public FoundExcepiton() {
    }

    public FoundExcepiton(String message) {
        super(message);
    }

    public FoundExcepiton(String message, Throwable cause) {
        super(message, cause);
    }
}
