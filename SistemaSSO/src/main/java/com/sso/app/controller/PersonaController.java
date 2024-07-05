package com.sso.app.controller;

import com.sso.app.entity.Persona;
import com.sso.app.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Persona>> getAllPersona(){
        return new ResponseEntity<>(this.personaService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id){
    return new ResponseEntity<>(this.personaService.findId(id),HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Persona> addPersona(@RequestBody Persona persona){
        return new ResponseEntity<>(this.personaService.addPersona(persona),HttpStatus.CREATED);
    }
}
