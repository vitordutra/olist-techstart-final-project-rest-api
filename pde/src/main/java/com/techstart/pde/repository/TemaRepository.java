package com.techstart.pde.repository;

import com.techstart.pde.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TemaRepository extends JpaRepository <Tema, Integer> {
    Optional<Tema> findByCodigo(Integer codigo);
    List<Tema> findByTimesOlistersMatricula(Integer matricula);
    List<Tema> findDistinctByTimesTemasCodigo(Integer codigo);
    List<Tema> findByTimesCoordenadorMatricula(Integer matricula);

}
