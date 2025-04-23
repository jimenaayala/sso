package com.sso.app.service;

import com.sso.app.entity.ensayo.EnsayoDv1;

import com.sso.app.repository.EnsayoDv1Repository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnsayoDv1Service {
    private final EnsayoDv1Repository ensayoDv1Repository;

    @Transactional
    public EnsayoDv1 crearEnsayoDv1 (EnsayoDv1 ensayo){
        return this.ensayoDv1Repository.save(ensayo);
    }

    @Transactional
    public EnsayoDv1 modificarEnsayoDv1 (Long id, EnsayoDv1 ensayo){
        Optional<EnsayoDv1> ensayoexistente = ensayoDv1Repository.findById(id);
        if(ensayoexistente.isPresent()){
            EnsayoDv1 ensayoDv1 = ensayoexistente.get();

            // Copia todas las propiedades excepto el ID
            BeanUtils.copyProperties(ensayo, ensayoDv1, "id");

            return this.ensayoDv1Repository.save(ensayoDv1);
        }else {
            throw new RuntimeException("Ensayo no encontrado con ID: " + id);
        }
    }

    // Obtener ensayo por ID
    public EnsayoDv1 obtenerEnsayoPorId(Long id) {
        return ensayoDv1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo no encontrado con ID: " + id));
    }

    // Obtener todas los ensayos
    public List<EnsayoDv1> obtenerTodosEnsayos() {
        return (List<EnsayoDv1>) this.ensayoDv1Repository.findAll();
    }
}
