package com.techstart.pde.service;

import com.techstart.pde.dto.OlisterDTO.OlisterDTO;
import com.techstart.pde.model.Olister;
import com.techstart.pde.model.Time;
import com.techstart.pde.repository.OlisterRepository;
import com.techstart.pde.repository.TimeRepository;
import com.techstart.pde.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OlisterService {
    
    @Autowired
	private OlisterRepository repository;
	@Autowired
	private TimeRepository timeRepository;

	@Transactional
	public OlisterDTO cadastrar(OlisterDTO dto) {
		Olister entity = new Olister();
		copyToEntityCreate(dto, entity);
		entity = repository.save(entity);
		return new OlisterDTO(entity, entity.getTime());
	}

	@Transactional(readOnly = true)
    public List<OlisterDTO> listarTodos(){
		List<OlisterDTO> listDTO = new ArrayList<>();
		List<Olister> list = repository.findAll();

		for (Olister olister : list) {
			OlisterDTO dto = new OlisterDTO(olister, olister.getTime());
			listDTO.add(dto);
		}
		return listDTO;
	}

	@Transactional(readOnly = true)
	public OlisterDTO encontrarPelaMatricula(Integer matricula) {
		Optional<Olister> obj = repository.findByMatricula(matricula);
		Olister entity = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
		return new OlisterDTO(entity, entity.getTime());
	}

	@Transactional
	public OlisterDTO atualizar(Integer matricula, OlisterDTO dto) {
		try {
			Optional<Olister> obj = repository.findByMatricula(matricula);
			Olister entity = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
			copyToEntityUpdate(dto, entity);
			entity = repository.save(entity);
			return new OlisterDTO(entity, entity.getTime());
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Matricula nao encontrada!");
		}
	}
	
	private void copyToEntityCreate(OlisterDTO dto, Olister entity) {
		entity.setMatricula(dto.getMatricula());
		copyToEntity(dto, entity);
	}

	private void copyToEntityUpdate(OlisterDTO dto, Olister entity) {
		copyToEntity(dto, entity);
	}

	private void copyToEntity(OlisterDTO dto, Olister entity) {
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());


		if (dto.getTime() != null) {
			Optional<Time> obj = timeRepository.findByCodigo(dto.getTime().getCodigo());
			Time time = obj.orElseThrow(() -> new ObjectNotFoundException("Entidade nao encontrada!"));
			entity.setTime(time);
		}
	}
}
