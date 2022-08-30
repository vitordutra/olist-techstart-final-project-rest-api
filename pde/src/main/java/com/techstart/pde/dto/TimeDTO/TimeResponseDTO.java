package com.techstart.pde.dto.TimeDTO;

import com.techstart.pde.model.Time;

public class TimeResponseDTO {
    private Integer codigo;
    private String nome;

    public TimeResponseDTO() {
    }

    public TimeResponseDTO(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public TimeResponseDTO(Time entity) {
        codigo = entity.getCodigo();
        nome = entity.getNome();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
