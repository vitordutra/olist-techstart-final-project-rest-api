package com.techstart.pde.controller;

import com.techstart.pde.dto.TemaDTO.TemaDTO;
import com.techstart.pde.service.TemaService;
import com.techstart.pde.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tema")
public class TemaController {
    @Autowired
    private TemaService service;

    @PostMapping("salva")
    public ResponseEntity<TemaDTO> cadastrar(@RequestBody TemaDTO dto) {
        return new ResponseEntity<>(service.cadastrar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TemaDTO>> listarTodosTemas() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<TemaDTO> listarTemaPorCodigo(@PathVariable Integer codigo) {
        try {
            TemaDTO tema = service.listarPorCodigo(codigo);
            return new ResponseEntity<>(tema, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("salva/{codigo}")
    public ResponseEntity<TemaDTO> atualizar(@PathVariable Integer codigo, @RequestBody TemaDTO dto) {
        return new ResponseEntity<>(service.atualizar(codigo, dto), HttpStatus.OK);
    }
}


  
    
  
	

