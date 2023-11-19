package br.com.project.SolarSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="planetas")
public class Planets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_planetas")
    private String nomePlanetas;
    @Column(name = "tamanho")
    private double tamanho;

    @OneToMany(mappedBy = "planets", cascade = CascadeType.PERSIST)
    public List<Moons> moons = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "star_id")
    public Star star;
}
