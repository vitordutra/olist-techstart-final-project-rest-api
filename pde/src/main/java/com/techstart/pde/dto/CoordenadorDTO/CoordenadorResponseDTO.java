package com.techstart.pde.dto.CoordenadorDTO;

import com.techstart.pde.model.Coordenador;

public class CoordenadorResponseDTO {
    private Integer matricula;
    private String nome;
    private String email;

    public CoordenadorResponseDTO() {
    }

    public CoordenadorResponseDTO(Integer matricula, String nome, String email) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    public CoordenadorResponseDTO(Coordenador entity) {
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
