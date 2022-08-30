package com.techstart.pde.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.techstart.pde.dto.TimeDTO.TimeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techstart.pde.dto.TemaDTO.TemaDTO;
import com.techstart.pde.model.Tema;
import com.techstart.pde.model.Time;
import com.techstart.pde.repository.TemaRepository;
import com.techstart.pde.repository.TimeRepository;
import com.techstart.pde.service.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TemaService {
    
    @Autowired
	private TemaRepository repository;

	@Autowired
	private TimeRepository timeRepository;

	public TemaDTO cadastrar(TemaDTO dto ) {
		Tema entity = new Tema();
		copyToEntityCreate(dto, entity);
		entity = repository.save(entity);
		return new TemaDTO(entity, entity.getTimes());
	}

    public List<TemaDTO> listarTodos(){
		List<TemaDTO> listDTO = new ArrayList<>();
		List<Tema> list = repository.findAll();

		for (Tema tema : list) {
			TemaDTO dto = new TemaDTO(tema, tema.getTimes());
			listDTO.add(dto);
		}
		return listDTO;
	}

	public TemaDTO listarPorCodigo(Integer codigo) {
		Optional<Tema> obj = repository.findByCodigo(codigo);
		Tema entity = obj.orElseThrow(() -> new ObjectNotFoundException("Código não encontrado. Tema não cadastrado."));
			return new TemaDTO(entity,entity.getTimes());
	}

	@Transactional
	public TemaDTO atualizar(Integer codigo, TemaDTO dto) {
		try {
			Optional<Tema> obj = repository.findByCodigo(codigo);
			Tema entity = obj.orElseThrow(() -> new ObjectNotFoundException("Tema não encontrado. Código	não cadastrado."));
			copyToEntityUpdate(dto, entity);
			entity = repository.save(entity);
			return new TemaDTO(entity, entity.getTimes());
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Time não encontrado.");
		}
	}

	private void copyToEntityCreate(TemaDTO dto, Tema entity) {
		entity.setCodigo(dto.getCodigo());
		copyToEntity(dto, entity);
	}

	private void copyToEntityUpdate(TemaDTO dto, Tema entity) {
		copyToEntity(dto, entity);
	}

	private void copyToEntity(TemaDTO dto, Tema entity) {
		entity.setDescricao(dto.getDescricao());

		entity.getTimes().clear();
		for (TimeResponseDTO timeDTO : dto.getTimes()) {
			Optional<Time> obj = timeRepository.findByCodigo(timeDTO.getCodigo());
			Time time = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
			entity.getTimes().add(time);
		}
	}
}



