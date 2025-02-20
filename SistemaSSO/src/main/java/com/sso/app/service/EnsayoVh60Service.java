package com.sso.app.service;


import com.sso.app.entity.ensayo.EnsayoVh60;
import com.sso.app.repository.EnsayoVH60Repository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnsayoVh60Service {
    private final EnsayoVH60Repository ensayoVH60Repository;

    @Transactional
    public EnsayoVh60 guardarEnsayo(EnsayoVh60 ensayo){
        return this.ensayoVH60Repository.save(ensayo);
    }

    @Transactional
    public EnsayoVh60 actualizarEnsayo(Long id,EnsayoVh60 ensayo){
       Optional<EnsayoVh60> ensayoEncontrado = ensayoVH60Repository.findById(id);
       if (ensayoEncontrado.isPresent()){
           EnsayoVh60 ensayoNuevo = ensayoEncontrado.get();

           ensayoNuevo.setRpm100(ensayo.getRpm100());
           ensayoNuevo.setRpm200(ensayo.getRpm200());
           ensayoNuevo.setRpm300(ensayo.getRpm300());

           ensayoNuevo.setCargaAxial(ensayo.getCargaAxial());
           ensayoNuevo.setFugaDeAceite(ensayo.getFugaDeAceite());
           ensayoNuevo.setNivelDeAceite(ensayo.getNivelDeAceite());
           ensayoNuevo.setNivelDeVibracion(ensayo.getNivelDeVibracion());
           ensayoNuevo.setNivelDeRuido(ensayo.getNivelDeRuido());
           ensayoNuevo.setTemperatura(ensayo.getTemperatura());
           ensayoNuevo.setPintura(ensayo.getPintura());

           return this.ensayoVH60Repository.save(ensayoNuevo);
       }else {
           throw new RuntimeException("Ensayo no encontrado con ID: " + id);
       }
    }
    // Obtener ensayo por ID
    public EnsayoVh60 obtenerEnsayoPorId(Long id) {
        return ensayoVH60Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo no encontrado con ID: " + id));
    }

    // Obtener todas los ensayos
    public List<EnsayoVh60> obtenerTodosEnsayos() {
        return (List<EnsayoVh60>) this.ensayoVH60Repository.findAll();
    }
}

