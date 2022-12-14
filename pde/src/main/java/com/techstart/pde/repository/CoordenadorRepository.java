package com.techstart.pde.repository;

import com.techstart.pde.model.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordenadorRepository extends JpaRepository<Coordenador, String> {
    Optional<Coordenador> findByMatricula(Integer matricula);
}
