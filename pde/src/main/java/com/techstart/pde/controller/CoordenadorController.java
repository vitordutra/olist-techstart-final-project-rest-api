package com.techstart.pde.controller;

import com.techstart.pde.dto.CoordenadorDTO.CoordenadorDTO;
import com.techstart.pde.service.CoordenadorService;
import com.techstart.pde.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/coordenador")
public class CoordenadorController {
    @Autowired
    private CoordenadorService coordenadorService;

    @PostMapping("/salva")
    public ResponseEntity<CoordenadorDTO> cadastrarCoordenador(@RequestBody CoordenadorDTO dto){
        return new ResponseEntity<>(coordenadorService.cadastrar(dto), HttpStatus.CREATED);
    }

    @GetMapping("{matricula}")
    public ResponseEntity<CoordenadorDTO> encontrarTimePeloCodigo(@PathVariable Integer matricula) {
        try {
            CoordenadorDTO coordenador = this.coordenadorService.listarPelaMatricula(matricula);
            return new ResponseEntity<>(coordenador, HttpStatus.OK);
        } catch(ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CoordenadorDTO>> listarCoordenadores() {
        return new ResponseEntity <>(coordenadorService.listarTodos(), HttpStatus.OK);
    }

    @PutMapping("/salva/{matricula}") // para dizer que o metodo será PUT.
    public ResponseEntity<CoordenadorDTO> atualizarCoordenador(@PathVariable Integer matricula, @RequestBody CoordenadorDTO dto){
        return new ResponseEntity<>(coordenadorService.atualizarPelaMatricula(matricula, dto), HttpStatus.OK);
    }
}                
