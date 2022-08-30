package com.techstart.pde.dto.CoordenadorDTO;

import java.util.ArrayList;
import java.util.List;

import com.techstart.pde.dto.TimeDTO.TimeResponseDTO;
import com.techstart.pde.model.Coordenador;
import com.techstart.pde.model.Time;

public class CoordenadorDTO {
    private Integer matricula;
    private String nome;
    private String email;
    private final List<TimeResponseDTO> times = new ArrayList<>();

    public CoordenadorDTO() {
    }

    public CoordenadorDTO(Integer matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    public CoordenadorDTO(Coordenador entity) {
        matricula = entity.getMatricula();
        nome = entity.getNome();
        email = entity.getEmail();
    }

    public CoordenadorDTO(Coordenador entity, List<Time> times) {
        this(entity);
        times.forEach(time -> this.times.add(new TimeResponseDTO(time)));
    }
    
    public Integer getMatricula() {
        return matricula;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getEmail() {
        return email;
    }

    public List<TimeResponseDTO> getTimes() {
        return times;
    }
}
