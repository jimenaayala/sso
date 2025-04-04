package com.sso.app.service;

import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;

import com.sso.app.repository.InspeccionPcpVh60Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class InspeccionPcpVh60Service {

    private final InspeccionPcpVh60Repository inspeccionRepository;

    /**
     * Crear una nueva inspección (con entidades relacionadas por CascadeType.ALL)
     */
    @Transactional
    public InspeccionPcpVh60 crearInspeccion(InspeccionPcpVh60 inspeccion) {
        return inspeccionRepository.save(inspeccion);
    }

    /**
     * Actualizar una inspección existente por ID
     */
    @Transactional
    public InspeccionPcpVh60 actualizarInspeccion(Long id, InspeccionPcpVh60 inspeccionNueva) {
        InspeccionPcpVh60 inspeccionActual = inspeccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspección no encontrada con ID: " + id));

        inspeccionActual.setComentario(inspeccionNueva.getComentario());

        // Lubricantes
        inspeccionActual.setLubricanteBlockPortaRodamientos(inspeccionNueva.getLubricanteBlockPortaRodamientos());
        inspeccionActual.setLubricanteSistemaFreno(inspeccionNueva.getLubricanteSistemaFreno());

        // Ítems
        inspeccionActual.setEjeMotriz(inspeccionNueva.getEjeMotriz());
        inspeccionActual.setBlockCabezal(inspeccionNueva.getBlockCabezal());
        inspeccionActual.setPlacaInferior(inspeccionNueva.getPlacaInferior());
        inspeccionActual.setPlacaSuperior(inspeccionNueva.getPlacaSuperior());

        // Rodamientos
        inspeccionActual.setAxial294158(inspeccionNueva.getAxial294158());
        inspeccionActual.setGuiaSup6022(inspeccionNueva.getGuiaSup6022());
        inspeccionActual.setGuiaInf6017(inspeccionNueva.getGuiaInf6017());
        inspeccionActual.setFreno60051rsZ(inspeccionNueva.getFreno60051rsZ());
        inspeccionActual.setAntirretornoCsk25PpC3(inspeccionNueva.getAntirretornoCsk25PpC3());

        // Transmisión de freno
        inspeccionActual.setCorona(inspeccionNueva.getCorona());
        inspeccionActual.setPinion(inspeccionNueva.getPinion());
        inspeccionActual.setPastillasFreno(inspeccionNueva.getPastillasFreno());

        // Sistema hidráulico
        inspeccionActual.setBomba(inspeccionNueva.getBomba());
        inspeccionActual.setManifold(inspeccionNueva.getManifold());
        inspeccionActual.setCaliper(inspeccionNueva.getCaliper());
        inspeccionActual.setConjuntoMangueras(inspeccionNueva.getConjuntoMangueras());

        // Polea
        inspeccionActual.setPolea(inspeccionNueva.getPolea());

        return inspeccionRepository.save(inspeccionActual);
    }

    /**
     * Obtener inspección por ID
     */
    public InspeccionPcpVh60 obtenerPorId(Long id) {
        return inspeccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspección no encontrada con ID: " + id));
    }

    /**
     * Obtener todas las inspecciones
     */
    public List<InspeccionPcpVh60> obtenerTodas() {
        return inspeccionRepository.findAll();
    }
}
