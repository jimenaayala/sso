package com.sso.app.service;

import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import com.sso.app.repository.InspeccionPcpCoguarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InspeccionPcpCoguarService {

    @Autowired
    private InspeccionPcpCoguarRepository inspeccionRepository;

    // Crear una nueva inspección con entidades relacionadas
    @Transactional
    public InspeccionPcpCougar crearInspeccion(InspeccionPcpCougar inspeccion) {
        // Al guardar inspeccion, también se guardan las entidades relacionadas gracias a CascadeType.ALL
        return inspeccionRepository.save(inspeccion);
    }

    // Actualizar una inspección existente
    @Transactional
    public InspeccionPcpCougar actualizarInspeccion(Long id, InspeccionPcpCougar inspeccionNueva) {
        Optional<InspeccionPcpCougar> inspeccionExistente = inspeccionRepository.findById(id);

        if (inspeccionExistente.isPresent()) {
            InspeccionPcpCougar inspeccion = inspeccionExistente.get();

            // Actualizamos cada entidad relacionada
            inspeccion.setComentario(inspeccionNueva.getComentario());
            inspeccion.setLubricantePcpCoguar(inspeccionNueva.getLubricantePcpCoguar());
            inspeccion.setItemPcpCoguar(inspeccionNueva.getItemPcpCoguar());
            inspeccion.setRodamientoPcpCoguar(inspeccionNueva.getRodamientoPcpCoguar());
            inspeccion.setTransmisionFrenoPcpCoguar(inspeccionNueva.getTransmisionFrenoPcpCoguar());
            inspeccion.setSistemaHidraulicoPcpCoguar(inspeccionNueva.getSistemaHidraulicoPcpCoguar());
            inspeccion.setPoleaPcpCoguar(inspeccionNueva.getPoleaPcpCoguar());

            return inspeccionRepository.save(inspeccion);
        } else {
            throw new RuntimeException("Inspección no encontrada con ID: " + id);
        }
    }

    // Obtener inspección por ID
    public InspeccionPcpCougar obtenerInspeccionPorId(Long id) {
        return inspeccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspección no encontrada con ID: " + id));
    }
    // Obtener todas las inspecciones
    public List<InspeccionPcpCougar> obtenerTodasInspecciones() {
        return inspeccionRepository.findAll();
    }
}
