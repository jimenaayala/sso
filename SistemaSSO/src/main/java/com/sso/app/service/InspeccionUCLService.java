package com.sso.app.service;

import com.sso.app.entity.InspeccionUCL.InspeccionUCL;
import com.sso.app.repository.UCL.InspeccionUCLRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InspeccionUCLService {

    private final InspeccionUCLRepository inspeccionUCLRepository;

    @Transactional
    public InspeccionUCL crearInspeccionUCL(InspeccionUCL inspeccionUCL){
        return inspeccionUCLRepository.save(inspeccionUCL);
    }

    @Transactional
    public InspeccionUCL actualizarInspeccionUCL(Long id, InspeccionUCL inspeccionUCL){

        Optional<InspeccionUCL> inspeccionExistenteUCL = inspeccionUCLRepository.findById(id);

        if(inspeccionExistenteUCL.isPresent()){
            InspeccionUCL inspeccion = inspeccionExistenteUCL.get();

            inspeccion.setComentario(inspeccionUCL.getComentario());
            inspeccion.setLubricanteUCL(inspeccionUCL.getLubricanteUCL());
            inspeccion.setDispositivoInversor(inspeccionUCL.getDispositivoInversor());
            inspeccion.setTransmisionUCL(inspeccionUCL.getTransmisionUCL());
            inspeccion.setEstructuraUCL(inspeccionUCL.getEstructuraUCL());
            inspeccion.setSistemaSeguridad(inspeccionUCL.getSistemaSeguridad());

            return inspeccionUCLRepository.save(inspeccion);

        }else{
            throw new RuntimeException("Inspeccion UCL no encontrada con el ID: " + id);

        }
    }

    public InspeccionUCL obtenerInspeccionUCLPorId(Long id){
        return inspeccionUCLRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspeccion UCL no encontrada con el ID " + id));
    }

    public List<InspeccionUCL> obtenerTodasInspeccionesUCL(){return inspeccionUCLRepository.findAll();}
}
