package com.sso.app.service;

import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDV1;
import com.sso.app.repository.InspeccionPcpDv1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InspeccionPcpDv1Service {

    @Autowired
    private InspeccionPcpDv1Repository inspeccionRepository;

    // Crear una nueva inspección con entidades relacionadas
    @Transactional
    public InspeccionPcpDV1 crearInspeccion(InspeccionPcpDV1 inspeccion) {
        // Al guardar inspeccion, también se guardan las entidades relacionadas gracias a CascadeType.ALL
        return inspeccionRepository.save(inspeccion);
    }

    // Actualizar una inspección existente
    @Transactional
    public InspeccionPcpDV1 actualizarInspeccion(Long id, InspeccionPcpDV1 inspeccionNueva) {
        Optional<InspeccionPcpDV1> inspeccionExistente = inspeccionRepository.findById(id);

        if (inspeccionExistente.isPresent()) {
            InspeccionPcpDV1 inspeccion = inspeccionExistente.get();

            // Actualizamos cada entidad relacionada
            inspeccion.setComentario(inspeccionNueva.getComentario());
            inspeccion.setLubricantePcpDV1(inspeccionNueva.getLubricantePcpDV1());
            inspeccion.setItemPcpDV1(inspeccionNueva.getItemPcpDV1());
            inspeccion.setRodamientoPcpDV1(inspeccionNueva.getRodamientoPcpDV1());
            inspeccion.setTransmisionFrenoPcpDv1(inspeccionNueva.getTransmisionFrenoPcpDv1());
            inspeccion.setSistemaHidraulicoPcpDV1(inspeccionNueva.getSistemaHidraulicoPcpDV1());
            inspeccion.setPoleaPcpDV1(inspeccionNueva.getPoleaPcpDV1());

            return inspeccionRepository.save(inspeccion);
        } else {
            throw new RuntimeException("Inspección no encontrada con ID: " + id);
        }
    }

    // Obtener inspección por ID
    public InspeccionPcpDV1 obtenerInspeccionPorId(Long id) {
        return inspeccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspección no encontrada con ID: " + id));
    }
    // Obtener todas las inspecciones
    public List<InspeccionPcpDV1> obtenerTodasInspecciones() {
        return inspeccionRepository.findAll();
    }
}
