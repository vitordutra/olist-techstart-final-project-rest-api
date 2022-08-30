package com.techstart.pde.dto.TimeDTO;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techstart.pde.dto.CoordenadorDTO.CoordenadorDTO;
import com.techstart.pde.dto.CoordenadorDTO.CoordenadorResponseDTO;
import com.techstart.pde.dto.OlisterDTO.OlisterDTO;
import com.techstart.pde.dto.OlisterDTO.OlisterResponseDTO;
import com.techstart.pde.dto.TemaDTO.TemaDTO;
import com.techstart.pde.dto.TemaDTO.TemaResponseDTO;
import com.techstart.pde.model.Coordenador;
import com.techstart.pde.model.Olister;
import com.techstart.pde.model.Tema;
import com.techstart.pde.model.Time;

public class TimeDTO {
    private Integer codigo;
    private String nome;
    private final List<OlisterResponseDTO> olisters = new ArrayList<>();
    private CoordenadorResponseDTO coordenador;
    private final List<TemaResponseDTO> temas = new ArrayList<>();

    public TimeDTO() {
    }

    public TimeDTO(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public TimeDTO(Time entity) {
        codigo = entity.getCodigo();
        nome = entity.getNome();
    }

    public TimeDTO(Time entity, List<Olister> olisters, Coordenador coordenador, List<Tema> temas) {
        this(entity);
        olisters.forEach(olister -> this.olisters.add(new OlisterResponseDTO(olister)));
        this.coordenador = new CoordenadorResponseDTO(coordenador);
        temas.forEach(tema -> this.temas.add(new TemaResponseDTO(tema)));
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<OlisterResponseDTO> getOlisters() {
        return olisters;
    }

    public CoordenadorResponseDTO getCoordenador() {
        return coordenador;
    }

    public List<TemaResponseDTO> getTemas() {
        return temas;
    }
}
