package com.techstart.pde.controller;

import com.techstart.pde.dto.OlisterDTO.OlisterDTO;
import com.techstart.pde.service.OlisterService;
import com.techstart.pde.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("olister")
public class OlisterController {
    @Autowired
    private OlisterService service;

    @PostMapping("salva")
    public ResponseEntity<OlisterDTO> cadastrar(@RequestBody OlisterDTO dto) {
        return new ResponseEntity<>(service.cadastrar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OlisterDTO>> encontrarTodos() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("{matricula}")
    public ResponseEntity<OlisterDTO> encontrarPelaMatricula(@PathVariable Integer matricula) {
        try {
            OlisterDTO olister = service.encontrarPelaMatricula(matricula);
            return new ResponseEntity<>(olister, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("salva/{matricula}")
    public ResponseEntity<OlisterDTO> atualizar(@PathVariable Integer matricula, @RequestBody OlisterDTO dto) {
        return new ResponseEntity<>(service.atualizar(matricula, dto), HttpStatus.OK);
    }
}
