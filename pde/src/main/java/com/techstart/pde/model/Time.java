package com.techstart.pde.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 20, unique = true)
    private Integer codigo;
    @Column(nullable = false, length = 40)
    private String nome;
    @OneToMany(mappedBy = "time")
    private List<Olister> olisters = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "matricula_coordenador_id")
    private Coordenador coordenador;
    @ManyToMany
    @JoinTable(
        name="times_temas",
        joinColumns = @JoinColumn(name="time_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "tema_id", referencedColumnName = "id")
    )
    private List<Tema> temas= new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Olister> getOlisters() {
        return olisters;
    }

    public void setOlisters(List<Olister> olisters) {
        this.olisters.addAll(olisters);
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas.addAll(temas);
    }
}
