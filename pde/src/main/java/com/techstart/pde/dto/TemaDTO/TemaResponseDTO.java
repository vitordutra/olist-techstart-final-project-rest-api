package com.techstart.pde.dto.TemaDTO;

import com.techstart.pde.model.Tema;

public class TemaResponseDTO {
    private Integer codigo;
    private String descricao;

    public TemaResponseDTO() {
    }

    public TemaResponseDTO(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public TemaResponseDTO(Tema entity) {
        codigo = entity.getCodigo();
        descricao = entity.getDescricao();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
