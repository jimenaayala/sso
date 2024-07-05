package com.sso.app.service;

import com.sso.app.entity.Persona;
import com.sso.app.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> findAll(){
        return this.personaRepository.findAll();
    }
    public Persona findId(Long id){
        return this.personaRepository.findById(id).orElse(new Persona());
    }
    public Persona addPersona(Persona persona){
        return this.personaRepository.save(persona);
    }
}
