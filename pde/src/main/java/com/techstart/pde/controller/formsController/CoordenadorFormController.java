package com.techstart.pde.controller.formsController;

import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.techstart.pde.model.Coordenador;
import com.techstart.pde.repository.CoordenadorRepository;

@Controller
public class CoordenadorFormController {
    
    @Autowired
    private CoordenadorRepository coordenadorRepository;
  
    @RequestMapping(path = "/cadastrarcoordenador")
    public String cadastrarCoordenador(){
        return "formCoordenador.html";
    }

    @PostMapping("/cadastrarcoordenador")
    @Transactional
    public ResponseEntity<Coordenador> cadastrar(@RequestParam("matricula") Integer matricula, @RequestParam("nome") String nome, @RequestParam("email") String email,UriComponentsBuilder uriBuilder){
        Coordenador coordenador = new Coordenador();
        coordenador.setMatricula(matricula);
        coordenador.setNome(nome);
        coordenador.setEmail(email);

        coordenadorRepository.save(coordenador);
        URI uri = uriBuilder.path("/coordenador/codigo}").buildAndExpand(coordenador.getMatricula()).toUri();
        return ResponseEntity.created(uri).body(coordenador); 
    }
}
