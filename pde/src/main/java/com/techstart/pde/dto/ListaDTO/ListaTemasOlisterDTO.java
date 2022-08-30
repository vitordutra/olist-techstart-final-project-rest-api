package com.techstart.pde.dto.ListaDTO;

import com.techstart.pde.dto.TemaDTO.TemaResponseDTO;
import com.techstart.pde.model.Tema;

import java.util.ArrayList;
import java.util.List;

public class ListaTemasOlisterDTO {
    private final List<TemaResponseDTO> temas = new ArrayList<>();

    public ListaTemasOlisterDTO(List<Tema> temas) {
        temas.forEach(tema -> this.temas.add(new TemaResponseDTO(tema)));
    }

    public List<TemaResponseDTO> getTemas() {
        return temas;
    }
}
