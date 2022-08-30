package com.techstart.pde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techstart.pde.dto.ListaTemasTimeDTO;
import com.techstart.pde.dto.ListaDTO.ListaTemasOlisterDTO;
import com.techstart.pde.model.Tema;
import com.techstart.pde.repository.CoordenadorRepository;
import com.techstart.pde.repository.OlisterRepository;
import com.techstart.pde.repository.TemaRepository;
import com.techstart.pde.repository.TimeRepository;

@Service
public class ListarService {
    @Autowired
    CoordenadorRepository coordenadorRepository;
    @Autowired
    OlisterRepository olisterRepository;
    @Autowired
    TemaRepository temaRepository;
    @Autowired
    TimeRepository timeRepository;

    @Transactional(readOnly = true)
    public ListaTemasOlisterDTO encontrarTemaOlisterPelaMatricula(Integer matricula) {
        List<Tema> obj = temaRepository.findByTimesOlistersMatricula(matricula);
        return new ListaTemasOlisterDTO(obj);
    }
    
    @Transactional(readOnly = true)
    public ListaTemasTimeDTO encontrarTemaTimePeloCodigo(Integer codigo) {
        List<Tema> obj = temaRepository.findDistinctByTimesTemasCodigo(codigo);
        return new ListaTemasTimeDTO(obj);
    }

}
