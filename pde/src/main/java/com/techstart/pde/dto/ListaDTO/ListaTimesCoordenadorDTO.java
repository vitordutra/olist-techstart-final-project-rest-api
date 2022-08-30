package com.techstart.pde.dto.ListaDTO;

import com.techstart.pde.dto.OlisterDTO.OlisterDTO;
import com.techstart.pde.dto.OlisterDTO.OlisterResponseDTO;
import com.techstart.pde.dto.TemaDTO.TemaDTO;
import com.techstart.pde.dto.TemaDTO.TemaResponseDTO;
import com.techstart.pde.dto.TimeDTO.TimeDTO;
import com.techstart.pde.dto.TimeDTO.TimeResponseDTO;
import com.techstart.pde.model.Coordenador;
import com.techstart.pde.model.Olister;
import com.techstart.pde.model.Tema;
import com.techstart.pde.model.Time;

import java.util.ArrayList;
import java.util.List;

public class ListaTimesCoordenadorDTO {
    private Integer matricula;
    private String nome;
    private String email;
    private final List<TimeResponseDTO> times = new ArrayList<>();
    private final List<OlisterResponseDTO> olisters = new ArrayList<>();
    private final List<TemaResponseDTO> temas = new ArrayList<>();

    public ListaTimesCoordenadorDTO() {
    }

    public ListaTimesCoordenadorDTO(Integer matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    public ListaTimesCoordenadorDTO(Coordenador entity) {
        matricula = entity.getMatricula();
        nome = entity.getNome();
        email = entity.getEmail();
    }

    public ListaTimesCoordenadorDTO(Coordenador entity, List<Time> times, List<Olister> olisters, List<Tema> temas) {
        this(entity);
        times.forEach(time-> this.times.add(new TimeResponseDTO(time)));
        olisters.forEach(olister -> this.olisters.add(new OlisterResponseDTO(olister)));
        temas.forEach(tema -> this.temas.add(new TemaResponseDTO(tema)));
    }

    public String getNome() {
        return nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String getEmail() {
        return email;
    }

    public List<OlisterResponseDTO> getOlisters() {
        return olisters;
    }

    public List<TemaResponseDTO> getTemas() {
        return temas;
    }

    public List<TimeResponseDTO> getTimes() {
        return times;
    }
}
