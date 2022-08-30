package com.techstart.pde.dto.OlisterDTO;

import com.techstart.pde.dto.TimeDTO.TimeResponseDTO;
import com.techstart.pde.model.Olister;
import com.techstart.pde.model.Time;

public class OlisterDTO {
    private Integer matricula;
    private String nome;
    private String email;
    private TimeResponseDTO time;

    public OlisterDTO() {
    }

    public OlisterDTO(Integer matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    public OlisterDTO(Olister entity) {
        matricula = entity.getMatricula();
        nome = entity.getNome();
        email = entity.getEmail();
    }

    public OlisterDTO(Olister entity, Time time) {
        this(entity);
        this.time = new TimeResponseDTO(time);
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

    public TimeResponseDTO getTime() {
        return time;
    }
}
