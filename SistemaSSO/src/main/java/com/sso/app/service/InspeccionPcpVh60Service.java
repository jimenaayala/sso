package com.sso.app.service;

import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import com.sso.app.repository.InspeccionPcpVh60Repository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InspeccionPcpVh60Service {


    private final InspeccionPcpVh60Repository inspeccionRepository;

    // Crear una nueva inspección con entidades relacionadas
    @Transactional
    public InspeccionPcpVh60 crearInspeccion(InspeccionPcpVh60 inspeccion) {
        // Al guardar inspeccion, también se guardan las entidades relacionadas gracias a CascadeType.ALL
        return inspeccionRepository.save(inspeccion);
    }

    // Actualizar una inspección existente
    @Transactional
    public InspeccionPcpVh60 actualizarInspeccion(Long id, InspeccionPcpVh60 inspeccionNueva) {
        Optional<InspeccionPcpVh60> inspeccionExistente = inspeccionRepository.findById(id);

        if (inspeccionExistente.isPresent()) {
            InspeccionPcpVh60 inspeccion = inspeccionExistente.get();

            // Actualizamos cada entidad relacionada
            inspeccion.setComentario(inspeccionNueva.getComentario());
            inspeccion.setLubricantePcpVh60(inspeccionNueva.getLubricantePcpVh60());
            inspeccion.setItemPcpVh60(inspeccionNueva.getItemPcpVh60());
            inspeccion.setRodamientoPcpVh60(inspeccionNueva.getRodamientoPcpVh60());
            inspeccion.setTransmisionFrenoPcpVh60(inspeccionNueva.getTransmisionFrenoPcpVh60());
            inspeccion.setSistemaHidraulicoPcpVh60(inspeccionNueva.getSistemaHidraulicoPcpVh60());
            inspeccion.setPoleaPcpVh60(inspeccionNueva.getPoleaPcpVh60());

            return inspeccionRepository.save(inspeccion);
        } else {
            throw new RuntimeException("Inspección no encontrada con ID: " + id);
        }
    }

    // Obtener inspección por ID
    public InspeccionPcpVh60 obtenerInspeccionPorId(Long id) {
        return inspeccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspección no encontrada con ID: " + id));
    }
    // Obtener todas las inspecciones
    public List<InspeccionPcpVh60> obtenerTodasInspecciones() {
        return inspeccionRepository.findAll();
    }

}
