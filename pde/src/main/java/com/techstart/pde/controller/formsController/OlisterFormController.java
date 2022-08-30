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

import com.techstart.pde.model.Olister;
import com.techstart.pde.repository.OlisterRepository;

@Controller
public class OlisterFormController {
       
    @Autowired
    private OlisterRepository olisterRepository;
  
    @RequestMapping(path = "/cadastrarolister")
    public String cadastrarOlister(){
        return "formOlister.html";
    }

    @PostMapping("/cadastrarolister")
    @Transactional
    public ResponseEntity<Olister> cadastrar(@RequestParam("matricula") Integer matricula, @RequestParam("nome") String nome, @RequestParam("email") String email,UriComponentsBuilder uriBuilder){
        Olister olister = new Olister();
        olister.setMatricula(matricula);
        olister.setNome(nome);
        olister.setEmail(email);

        olisterRepository.save(olister);
        URI uri = uriBuilder.path("/olister/codigo}").buildAndExpand(olister.getMatricula()).toUri();
        return ResponseEntity.created(uri).body(olister); 
    }
}
