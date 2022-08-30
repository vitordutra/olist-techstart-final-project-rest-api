package com.techstart.pde.dto.OlisterDTO;

import com.techstart.pde.model.Olister;

public class OlisterResponseDTO {
    private Integer matricula;
    private String nome;
    private String email;

    public OlisterResponseDTO(Olister entity) {
        matricula = entity.getMatricula();
        nome = entity.getNome();
        email = entity.getEmail();
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
}
