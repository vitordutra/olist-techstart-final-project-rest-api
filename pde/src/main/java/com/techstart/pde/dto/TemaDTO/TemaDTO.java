package com.techstart.pde.dto.TemaDTO;

import java.util.ArrayList;
import java.util.List;

import com.techstart.pde.dto.TimeDTO.TimeResponseDTO;
import com.techstart.pde.model.Tema;
import com.techstart.pde.model.Time;

public class TemaDTO {
    private Integer codigo;
    private String descricao;
    private final List<TimeResponseDTO> times = new ArrayList<>();

    public TemaDTO() {
    }

    public TemaDTO(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public TemaDTO(Tema entity) {
        codigo = entity.getCodigo();
        descricao = entity.getDescricao();
    }

    public TemaDTO(Tema entity, List<Time> times) {
        this(entity);
        times.forEach(time -> this.times.add(new TimeResponseDTO(time)));
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<TimeResponseDTO> getTimes() {
        return times;
    }
}
