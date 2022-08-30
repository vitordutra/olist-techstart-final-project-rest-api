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

import com.techstart.pde.model.Tema;
import com.techstart.pde.repository.TemaRepository;

@Controller
public class TemaFormController {
    
    @Autowired
    private TemaRepository temaRepository;
  
    @RequestMapping(path = "/cadastrartema")
    public String cadastrarTema(){
        return "formTema.html";
    }

    @PostMapping("/cadastrartema")
    @Transactional
    public ResponseEntity<Tema> cadastrar(@RequestParam("codigo") Integer codigo, @RequestParam("descricao") String descricao, UriComponentsBuilder uriBuilder){
        Tema tema = new Tema();
        tema.setCodigo(codigo);
        tema.setDescricao(descricao);

        temaRepository.save(tema);
        URI uri = uriBuilder.path("/tema/codigo}").buildAndExpand(tema.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(tema); 
    }
}
