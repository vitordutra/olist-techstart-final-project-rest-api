package com.techstart.pde.repository;


import com.techstart.pde.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimeRepository extends JpaRepository<Time, Integer> {
    Optional<Time> findByCodigo(Integer codigo);
    List<Time> findByCoordenadorMatricula(Integer matricula);
}
