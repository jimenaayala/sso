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
            inspeccion.setLubricanteBlockPortaRodamientos(inspeccionNueva.getLubricanteBlockPortaRodamientos());
            inspeccion.setLubricanteEngranajes(inspeccionNueva.getLubricanteEngranajes());
            inspeccion.setLubricanteSistemaFreno(inspeccionNueva.getLubricanteSistemaFreno());
            inspeccion.setEjeMotriz(inspeccionNueva.getEjeMotriz());
            inspeccion.setBlockCabezal(inspeccionNueva.getBlockCabezal());
            inspeccion.setPlacaInferior(inspeccionNueva.getPlacaInferior());
            inspeccion.setProtectorEngranaje(inspeccionNueva.getProtectorEngranaje());
            inspeccion.setAxial29415(inspeccionNueva.getAxial29415());
            inspeccion.setGuiaSup6212(inspeccionNueva.getGuiaSup6212());
            inspeccion.setGuiaInf6212(inspeccionNueva.getGuiaInf6212());
            inspeccion.setCorona6212Z(inspeccionNueva.getCorona6212Z());
            inspeccion.setMHidr6210Z(inspeccionNueva.getMHidr6210Z());
            inspeccion.setCorona(inspeccionNueva.getCorona());
            inspeccion.setPinon(inspeccionNueva.getPinon());
            inspeccion.setCatraca(inspeccionNueva.getCatraca());
            inspeccion.setBolas(inspeccionNueva.getBolas());
            inspeccion.setManifold(inspeccionNueva.getManifold());
            inspeccion.setConjuntoMangueras(inspeccionNueva.getConjuntoMangueras());
            inspeccion.setPolea(inspeccionNueva.getPolea());

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
