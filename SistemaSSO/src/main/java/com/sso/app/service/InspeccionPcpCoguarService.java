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

            // EstadoLubricante
            inspeccion.setLubricanteBlockRodamientos(inspeccionNueva.getLubricanteBlockRodamientos());
            inspeccion.setLubricanteSistemaFreno(inspeccionNueva.getLubricanteSistemaFreno());

            // EstadoItem
            inspeccion.setEjeMotriz(inspeccionNueva.getEjeMotriz());
            inspeccion.setEjeSecundario(inspeccionNueva.getEjeSecundario());
            inspeccion.setBlockCabezal(inspeccionNueva.getBlockCabezal());
            inspeccion.setPlacaInterior(inspeccionNueva.getPlacaInterior());

            // EstadoRodamiento
            inspeccion.setCargaAxial(inspeccionNueva.getCargaAxial());
            inspeccion.setGuiaSup(inspeccionNueva.getGuiaSup());
            inspeccion.setGuiaInf(inspeccionNueva.getGuiaInf());
            inspeccion.setEjeSecSup(inspeccionNueva.getEjeSecSup());
            inspeccion.setEjeSecInf(inspeccionNueva.getEjeSecInf());
            inspeccion.setAcoBomHidra(inspeccionNueva.getAcoBomHidra());
            inspeccion.setTorrBomHid(inspeccionNueva.getTorrBomHid());

            // EstadoTransmision
            inspeccion.setCorona(inspeccionNueva.getCorona());
            inspeccion.setPinion(inspeccionNueva.getPinion());
            inspeccion.setRodJauAnti(inspeccionNueva.getRodJauAnti());

            // EstadoSistema
            inspeccion.setBomba(inspeccionNueva.getBomba());
            inspeccion.setValvFren(inspeccionNueva.getValvFren());

            // EstadoItemPolea
            inspeccion.setPolea(inspeccionNueva.getPolea());

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
