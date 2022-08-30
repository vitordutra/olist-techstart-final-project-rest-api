package com.techstart.pde.service;

import com.techstart.pde.dto.CoordenadorDTO.CoordenadorDTO;
import com.techstart.pde.dto.TimeDTO.TimeResponseDTO;
import com.techstart.pde.model.Coordenador;
import com.techstart.pde.model.Time;
import com.techstart.pde.repository.CoordenadorRepository;
import com.techstart.pde.repository.TimeRepository;
import com.techstart.pde.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository repository;
    @Autowired
    private TimeRepository timeRepository;

    @Transactional
    public CoordenadorDTO cadastrar(CoordenadorDTO dto) {
        Coordenador entity = new Coordenador();
        copyToEntityCreate(dto, entity);
        entity = repository.save(entity);
        return new CoordenadorDTO(entity, entity.getTimes());
    }

    @Transactional(readOnly = true)
    public List<CoordenadorDTO> listarTodos() {
        List<CoordenadorDTO> listaDTO = new ArrayList<>();
        List<Coordenador> lista = repository.findAll();
        for (Coordenador coordenador : lista) {
            CoordenadorDTO dto = new CoordenadorDTO(coordenador, coordenador.getTimes());
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @Transactional(readOnly = true)
    public CoordenadorDTO listarPelaMatricula(Integer matricula) {
        Optional<Coordenador> obj = repository.findByMatricula(matricula);
        Coordenador entity = obj.orElseThrow(() -> new ObjectNotFoundException(
                "Coordenador não encontrado! matricula: " + matricula + ", Tipo: " + Coordenador.class.getName(), null));
        return new CoordenadorDTO(entity, entity.getTimes());
    }

    @Transactional
    public CoordenadorDTO atualizarPelaMatricula(Integer matricula, CoordenadorDTO dto) {
        try {
            Optional<Coordenador> obj = repository.findByMatricula(matricula);
            Coordenador entity = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
            copyToEntityUpdate(dto, entity);
            entity = repository.save(entity);
            return new CoordenadorDTO(entity, entity.getTimes());
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Matricula nao encontrada!");
        }
    }

    private void copyToEntityCreate(CoordenadorDTO dto, Coordenador entity) {
        entity.setMatricula(dto.getMatricula());
        copyToEntity(dto, entity);
    }

    private void copyToEntityUpdate(CoordenadorDTO dto, Coordenador entity) {
        copyToEntity(dto, entity);
    }

    private void copyToEntity(CoordenadorDTO dto, Coordenador entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.getTimes().clear();
        for (TimeResponseDTO timeDTO : dto.getTimes()) {
            Optional<Time> obj = timeRepository.findByCodigo(timeDTO.getCodigo());
            Time time = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
            entity.getTimes().add(time);
        }
    }
}
