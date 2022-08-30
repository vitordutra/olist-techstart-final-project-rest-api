package com.techstart.pde.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.techstart.pde.dto.ListaDTO.ListaTimesCoordenadorDTO;
import com.techstart.pde.dto.OlisterDTO.OlisterResponseDTO;
import com.techstart.pde.dto.TemaDTO.TemaResponseDTO;
import com.techstart.pde.dto.TimeDTO.TimeDTO;
import com.techstart.pde.model.Coordenador;
import com.techstart.pde.model.Olister;
import com.techstart.pde.model.Tema;
import com.techstart.pde.repository.CoordenadorRepository;
import com.techstart.pde.repository.OlisterRepository;
import com.techstart.pde.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techstart.pde.model.Time;
import com.techstart.pde.repository.TimeRepository;
import com.techstart.pde.service.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private OlisterRepository olisterRepository;
    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Transactional
    public TimeDTO cadastrar(TimeDTO dto) {
        Time entity = new Time();
        copyToEntityCreate(dto, entity);
        entity = timeRepository.save(entity);
        return new TimeDTO(entity, entity.getOlisters(), entity.getCoordenador(), entity.getTemas());
    }

    @Transactional(readOnly = true)
    public List<TimeDTO> listarTodos() {
        List<TimeDTO> dtoList = new ArrayList<>();
        List<Time> timeList = timeRepository.findAll();
        for (Time time : timeList) {
            TimeDTO dto = new TimeDTO(time, time.getOlisters(), time.getCoordenador(), time.getTemas());
            dtoList.add(dto);
        }
        return dtoList;
    }
    @Transactional(readOnly = true)
    public ListaTimesCoordenadorDTO encontrarTimesPelaMatriculaDoCoordenador(Integer matricula) {
        Optional<Coordenador> obj = coordenadorRepository.findByMatricula(matricula);
        Coordenador entity = obj.orElseThrow(()-> new ObjectNotFoundException("Coordenador não encontrado! matricula: " + matricula + ", " +
        "Tipo: " + Coordenador.class.getName(), null));
        List<Time> timesDoCoordenador = timeRepository.findByCoordenadorMatricula(matricula);
        List<Olister> membrosDoCoordenador = olisterRepository.findByTimeCoordenadorMatricula(matricula);
        List<Tema> temasDoCoordenador = temaRepository.findByTimesCoordenadorMatricula(matricula);
        return new ListaTimesCoordenadorDTO(entity, timesDoCoordenador,membrosDoCoordenador, temasDoCoordenador );
    }

    @Transactional(readOnly = true)
    public TimeDTO listarPeloCodigo(Integer codigo) {
        Optional<Time> obj = timeRepository.findByCodigo(codigo);
        Time entity = obj.orElseThrow(() ->new ObjectNotFoundException("Time não encontrado! codigo: " + codigo + ", " +
                                                                           "Tipo: " + Time.class.getName(), null));
        return new TimeDTO(entity, entity.getOlisters(), entity.getCoordenador(), entity.getTemas());
    }

    @Transactional
    public TimeDTO atualizarTimePeloCodigo(Integer codigo, TimeDTO dto) {
        try {
            Optional<Time> obj = timeRepository.findByCodigo(codigo);
            Time entity = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
            copyToEntityUpdate(dto, entity);
            entity = timeRepository.save(entity);
            return new TimeDTO(entity);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Codigo do Time nao encontrado!");
        }
    }

    private void copyToEntityCreate(TimeDTO dto, Time entity) {
        entity.setCodigo(dto.getCodigo());
        copytoEntity(dto, entity);
    }

    private void copyToEntityUpdate(TimeDTO dto, Time entity) {
        copytoEntity(dto, entity);
    }

    private void copytoEntity(TimeDTO dto, Time entity) {
        entity.setNome(dto.getNome());

        entity.getOlisters().clear();
        for (OlisterResponseDTO olisterDTO : dto.getOlisters()) {
            Optional<Olister> obj = olisterRepository.findByMatricula(olisterDTO.getMatricula());
            Olister olister = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
            entity.getOlisters().add(olister);
        }

        if (dto.getCoordenador() != null) {
            Optional<Coordenador> obj = coordenadorRepository.findByMatricula(dto.getCoordenador().getMatricula());
            Coordenador coordenador = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
            entity.setCoordenador(coordenador);
        }

        entity.getTemas().clear();
        for (TemaResponseDTO temaDTO : dto.getTemas()) {
            Optional<Tema> temaObj = temaRepository.findByCodigo(temaDTO.getCodigo());
            Tema tema = temaObj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
            entity.getTemas().add(tema);
        }
    }
}

