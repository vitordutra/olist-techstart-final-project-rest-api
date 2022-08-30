package com.techstart.pde.repository;

import com.techstart.pde.model.Olister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OlisterRepository extends JpaRepository<Olister, Integer> {
    Optional<Olister> findByMatricula(Integer matricula);
    List<Olister> findByTimeCoordenadorMatricula(Integer matricula);
}
