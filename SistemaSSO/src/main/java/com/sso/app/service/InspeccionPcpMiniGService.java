package com.sso.app.service;

import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import com.sso.app.repository.InspeccionPcpMiniGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InspeccionPcpMiniGService {

    @Autowired
    private InspeccionPcpMiniGRepository inspeccionRepository;

    // Crear una nueva inspección con entidades relacionadas
    @Transactional
    public InspeccionPcpMiniG crearInspeccion(InspeccionPcpMiniG inspeccion) {
        // Al guardar inspección, también se guardan las entidades relacionadas gracias a CascadeType.ALL
        return inspeccionRepository.save(inspeccion);
    }

    // Actualizar una inspección existente
    @Transactional
    public InspeccionPcpMiniG actualizarInspeccion(Long id, InspeccionPcpMiniG inspeccionNueva) {
        Optional<InspeccionPcpMiniG> inspeccionExistente = inspeccionRepository.findById(id);

        if (inspeccionExistente.isPresent()) {
            InspeccionPcpMiniG inspeccion = inspeccionExistente.get();

            // Actualizamos cada entidad relacionada
            inspeccion.setComentario(inspeccionNueva.getComentario());
            inspeccion.setLubricanteBlockPortaRodamientos(inspeccionNueva.getLubricanteBlockPortaRodamientos());
            inspeccion.setEjeMotriz(inspeccionNueva.getEjeMotriz());
            inspeccion.setBlockCabezal(inspeccionNueva.getBlockCabezal());
            inspeccion.setPlacaInferior(inspeccionNueva.getPlacaInferior());
            inspeccion.setAxial29416(inspeccion.getAxial29416());
            inspeccion.setGuiaSuperiorNJ217(inspeccionNueva.getGuiaSuperiorNJ217());
            inspeccion.setGuiaInferiorNJ214(inspeccionNueva.getGuiaInferiorNJ214());
            inspeccion.setZapata61822(inspeccionNueva.getZapata61822());
            inspeccion.setFerodo(inspeccionNueva.getFerodo());
            inspeccion.setLevaS(inspeccionNueva.getLevaS());
            inspeccion.setVastagoDeResortes(inspeccionNueva.getVastagoDeResortes());
            inspeccion.setResortes(inspeccionNueva.getResortes());
            inspeccion.setPolea(inspeccionNueva.getPolea());


            return inspeccionRepository.save(inspeccion);
        } else {
            throw new RuntimeException("Inspección no encontrada con ID: " + id);
        }
    }

    // Obtener inspección por ID
    public InspeccionPcpMiniG obtenerInspeccionPorId(Long id) {
        return inspeccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspección no encontrada con ID: " + id));
    }

    // Obtener todas las inspecciones
    public List<InspeccionPcpMiniG> obtenerTodasInspecciones() {
        return inspeccionRepository.findAll();
    }
}
