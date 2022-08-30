package com.techstart.pde.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techstart.pde.dto.ListaTemasTimeDTO;
import com.techstart.pde.dto.ListaDTO.ListaTemasOlisterDTO;
import com.techstart.pde.service.ListarService;

@RestController
@RequestMapping("listar")
public class ListarController {
    @Autowired
    ListarService service;

    @GetMapping("temas/olister/{matricula}")
    public ResponseEntity<ListaTemasOlisterDTO> encontrarTemaOlisterPelaMatricula(@PathVariable Integer matricula) {
        ListaTemasOlisterDTO listaTemas = this.service.encontrarTemaOlisterPelaMatricula(matricula);
        return ResponseEntity.ok().body(listaTemas);
    }

    @GetMapping("temas/time/{codigo}")
    public ResponseEntity<ListaTemasTimeDTO> encontrarTemaTimePeloCodigo(@PathVariable Integer codigo) {
        ListaTemasTimeDTO listaTemas = service.encontrarTemaTimePeloCodigo(codigo);
        return ResponseEntity.ok().body(listaTemas);
    }
}
