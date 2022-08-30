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

import com.techstart.pde.model.Time;
import com.techstart.pde.repository.TimeRepository;

@Controller
public class TimeFormController {
    
    @Autowired
    private TimeRepository timeRepository;
  
    @RequestMapping(path = "/cadastrartime")
    public String cadastrarTime(){
        return "formTime.html";
    }

    @PostMapping("/cadastrartime")
    @Transactional
    public ResponseEntity<Time> cadastrar(@RequestParam("codigo") Integer codigo, @RequestParam("nome") String nome, UriComponentsBuilder uriBuilder){
        Time time = new Time();
        time.setCodigo(codigo);
        time.setNome(nome);

        timeRepository.save(time);
        URI uri = uriBuilder.path("/time/codigo}").buildAndExpand(time.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(time); 
    }

}
