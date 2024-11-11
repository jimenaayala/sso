package com.sso.app.service;

import com.sso.app.entity.Recepcion;
import com.sso.app.repository.InspeccionRepository;
import com.sso.app.repository.RecepcionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class RecepcionService {

    private final RecepcionRepository recepcionRepository;

    public Recepcion agregarRecepcion(Recepcion recepcion) throws IllegalArgumentException {
        if(recepcion.isOk()== recepcion.isFalta()){
        throw new RuntimeException("Ok y falta no pueden ser ambos true o ambos false");
        }

       return this.recepcionRepository.save(recepcion);
    }

    public Iterable<Recepcion> obtenerTodasLasRecepciones() {
        return this.recepcionRepository.findAll();
    }


}
