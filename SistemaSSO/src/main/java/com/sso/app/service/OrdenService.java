package com.sso.app.service;

import com.sso.app.entity.Orden;
import com.sso.app.repository.OrdenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdenService {
    private final OrdenRepository ordenRepository;

    public List<Orden> findAll(){
        return (List<Orden>) this.ordenRepository.findAll();
    }
    public Orden findById(Long id) {
        return ordenRepository.findById(id).orElse(null);
    }

    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    public void deleteById(Long id) {
        ordenRepository.deleteById(id);
    }
}
