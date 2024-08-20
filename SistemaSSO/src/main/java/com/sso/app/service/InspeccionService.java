package com.sso.app.service;

import com.sso.app.entity.Inspeccion;
import com.sso.app.repository.InspeccionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InspeccionService {

    private final InspeccionRepository inspeccionRepository;

    public Inspeccion save(Inspeccion inspeccion){ return this.inspeccionRepository.save(inspeccion);}

    //busca todas las que tengan el atributo eliminado en false.
    public List<Inspeccion> findAllActive (){ return (List<Inspeccion>) this.inspeccionRepository.findAllActive();}


    public Optional<Inspeccion> findById(Long id){
            return this.inspeccionRepository.findById(id);
    }


    //Realiza softdelete
    public void deletedById(Long id){
        Inspeccion inspeccion = inspeccionRepository.findById(id).orElseThrow(() -> new RuntimeException("Inspeccion no encontrada"));
        inspeccion.setEliminado(true);
        inspeccionRepository.save(inspeccion);
    }
}
