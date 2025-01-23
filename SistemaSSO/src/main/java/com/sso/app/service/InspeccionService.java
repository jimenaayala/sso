package com.sso.app.service;

import com.sso.app.entity.Inspeccion;
import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDV1;
import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import com.sso.app.repository.InspeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InspeccionService<T extends Inspeccion> {

    private final InspeccionRepository<T> inspeccionRepository;

    @Autowired
    public InspeccionService(InspeccionRepository<T> inspeccionRepository) {
        this.inspeccionRepository = inspeccionRepository;
    }

    @Transactional
    public T crearInspeccion(T inspeccion) {
        return inspeccionRepository.save(inspeccion);
    }

    @Transactional
    public T actualizarInspeccion(Long id, T inspeccionNueva) {
        Optional<T> inspeccionExistente = inspeccionRepository.findById(id);

        if (inspeccionExistente.isPresent()) {
            T inspeccion = inspeccionExistente.get();
            inspeccion.setComentario(inspeccionNueva.getComentario());
            inspeccion.setEliminado(inspeccionNueva.isEliminado());
            // Lógica específica para InspeccionPcpCougar
            if (inspeccion instanceof InspeccionPcpCougar && inspeccionNueva instanceof InspeccionPcpCougar) {
                InspeccionPcpCougar existingCougar = (InspeccionPcpCougar) inspeccion;
                InspeccionPcpCougar newCougar = (InspeccionPcpCougar) inspeccionNueva;
                existingCougar.setLubricantePcpCoguar(newCougar.getLubricantePcpCoguar());
                existingCougar.setItemPcpCoguar(newCougar.getItemPcpCoguar());
                // Copiar otros campos específicos
            }

            // Lógica específica para InspeccionPcpDV1
            else if (inspeccion instanceof InspeccionPcpDV1 && inspeccionNueva instanceof InspeccionPcpDV1) {
                InspeccionPcpDV1 existingDV1 = (InspeccionPcpDV1) inspeccion;
                InspeccionPcpDV1 newDV1 = (InspeccionPcpDV1) inspeccionNueva;
                existingDV1.setLubricantePcpDV1(newDV1.getLubricantePcpDV1());
                existingDV1.setItemPcpDV1(newDV1.getItemPcpDV1());
                // Copiar otros campos específicos
            }

            // Lógica específica para InspeccionPcpMiniG
            else if (inspeccion instanceof InspeccionPcpMiniG && inspeccionNueva instanceof InspeccionPcpMiniG) {
                InspeccionPcpMiniG existingMiniG = (InspeccionPcpMiniG) inspeccion;
                InspeccionPcpMiniG newMiniG = (InspeccionPcpMiniG) inspeccionNueva;
                existingMiniG.setLubricantePcpMiniG(newMiniG.getLubricantePcpMiniG());
                existingMiniG.setItemPcpMiniG(newMiniG.getItemPcpMiniG());
                // Copiar otros campos específicos
            }

            // Lógica específica para InspeccionPcpVh60
            else if (inspeccion instanceof InspeccionPcpVh60 && inspeccionNueva instanceof InspeccionPcpVh60) {
                InspeccionPcpVh60 existingVh60 = (InspeccionPcpVh60) inspeccion;
                InspeccionPcpVh60 newVh60 = (InspeccionPcpVh60) inspeccionNueva;
                existingVh60.setLubricantePcpVh60(newVh60.getLubricantePcpVh60());
                existingVh60.setItemPcpVh60(newVh60.getItemPcpVh60());
                // Copiar otros campos específicos
            }


            return inspeccionRepository.save(inspeccion);
        } else {
            throw new RuntimeException("Inspección no encontrada con ID: " + id);
        }
    }

    public T obtenerInspeccionPorId(Long id) {
        return inspeccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspección no encontrada con ID: " + id));
    }

    public List<T> obtenerTodasInspecciones() {
        return inspeccionRepository.findAll();
    }
}
