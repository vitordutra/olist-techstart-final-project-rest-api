package com.techstart.pde.controller;


import com.techstart.pde.dto.ListaDTO.ListaTimesCoordenadorDTO;
import com.techstart.pde.dto.TimeDTO.TimeDTO;
import com.techstart.pde.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @PostMapping("/salva")
    public ResponseEntity<TimeDTO> cadastrarTime(@RequestBody TimeDTO time){
        time = timeService.cadastrar(time);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/time/{codigo}").buildAndExpand(time.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(time);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<TimeDTO> encontrarTimePeloCodigo(@PathVariable Integer codigo){
        TimeDTO time = this.timeService.listarPeloCodigo(codigo);
        return ResponseEntity.ok().body(time);
    }

    @GetMapping("/coordenador/{matricula}")
    public ResponseEntity<ListaTimesCoordenadorDTO> encontrarTimesPelaMatriculaDoCoordenador(@PathVariable Integer matricula){
        ListaTimesCoordenadorDTO coordenador = this.timeService.encontrarTimesPelaMatriculaDoCoordenador(matricula);
        return ResponseEntity.ok().body(coordenador);
    }

    @GetMapping
    public ResponseEntity<List<TimeDTO>> listarTimes(){
        List<TimeDTO> list = timeService.listarTodos();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("salva/{codigo}")
    public ResponseEntity<TimeDTO> atualizarTime(@PathVariable Integer codigo, @RequestBody TimeDTO time){
        return new ResponseEntity<>(timeService.atualizarTimePeloCodigo(codigo, time), HttpStatus.OK);
    }
}                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                              
                                                                                                                                                                                                                                                                                                                                             
