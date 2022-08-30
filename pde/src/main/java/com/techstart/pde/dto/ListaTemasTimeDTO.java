package com.techstart.pde.dto;

import java.util.ArrayList;
import java.util.List;

import com.techstart.pde.dto.TemaDTO.TemaResponseDTO;
import com.techstart.pde.model.Tema;

public class ListaTemasTimeDTO {
    
    private List<TemaResponseDTO> temas = new ArrayList<TemaResponseDTO>();

    public ListaTemasTimeDTO() {
    }

    public ListaTemasTimeDTO(List<Tema> temas) {
        temas.forEach(tema -> this.temas.add(new TemaResponseDTO(tema)));
    }

    public List<TemaResponseDTO> getTemas() {
        return temas;
    }
}
