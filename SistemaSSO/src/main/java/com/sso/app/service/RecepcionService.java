package com.sso.app.service;

import com.sso.app.entity.ItemRecepcion;
import com.sso.app.entity.Recepcion;

import com.sso.app.repository.RecepcionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class RecepcionService {

    private final RecepcionRepository recepcionRepository;

    public RecepcionService(RecepcionRepository recepcionRepository) {
        this.recepcionRepository = recepcionRepository;
    }

    @Transactional
    public Recepcion guardarOActualizarRecepcion(Recepcion recepcion) {
        if (recepcion.getId() != null) {
            Optional<Recepcion> recepcionExistenteOpt = recepcionRepository.findById(recepcion.getId());

            if (recepcionExistenteOpt.isPresent()) {
                Recepcion recepcionExistente = recepcionExistenteOpt.get();
                actualizarCampos(recepcionExistente, recepcion);
                return recepcionRepository.save(recepcionExistente);
            }
        }
        return recepcionRepository.save(recepcion);
    }

    @Transactional
    private void actualizarCampos(Recepcion destino, Recepcion origen) {
        Optional.ofNullable(origen.getComentario()).ifPresent(destino::setComentario);
        if (origen.isEliminado()) {
            destino.setEliminado(true);
        }

        Optional.ofNullable(origen.getItemRecepcion()).ifPresent(itemOrigen -> {
            ItemRecepcion itemDestino = destino.getItemRecepcion();

            itemDestino.setCgestado(itemOrigen.isCgestado());
            Optional.ofNullable(itemOrigen.getCgrequerimiento()).ifPresent(itemDestino::setCgrequerimiento);
            Optional.ofNullable(itemOrigen.getCgobservacion()).ifPresent(itemDestino::setCgobservacion);

            itemDestino.setEstado(itemOrigen.isEstado());
            Optional.ofNullable(itemOrigen.getRequerimiento()).ifPresent(itemDestino::setRequerimiento);
            Optional.ofNullable(itemOrigen.getObservacion()).ifPresent(itemDestino::setObservacion);

            itemDestino.setCvestado(itemOrigen.isCvestado());
            Optional.ofNullable(itemOrigen.getCvrequerimiento()).ifPresent(itemDestino::setCvrequerimiento);
            Optional.ofNullable(itemOrigen.getCvobservacion()).ifPresent(itemDestino::setCvobservacion);

            itemDestino.setGaestado(itemOrigen.isGaestado());
            Optional.ofNullable(itemOrigen.getGarequerimiento()).ifPresent(itemDestino::setGarequerimiento);
            Optional.ofNullable(itemOrigen.getGaobservacion()).ifPresent(itemDestino::setGaobservacion);

            itemDestino.setEcestado(itemOrigen.isEcestado());
            Optional.ofNullable(itemOrigen.getEcrequerimiento()).ifPresent(itemDestino::setEcrequerimiento);
            Optional.ofNullable(itemOrigen.getEcobservacion()).ifPresent(itemDestino::setEcobservacion);

            itemDestino.setLsestado(itemOrigen.isLsestado());
            Optional.ofNullable(itemOrigen.getLsrequerimiento()).ifPresent(itemDestino::setLsrequerimiento);
            Optional.ofNullable(itemOrigen.getLsobservacion()).ifPresent(itemDestino::setLsobservacion);

            itemDestino.setMmestado(itemOrigen.isMmestado());
            Optional.ofNullable(itemOrigen.getMmrequerimiento()).ifPresent(itemDestino::setMmrequerimiento);
            Optional.ofNullable(itemOrigen.getMmobservacion()).ifPresent(itemDestino::setMmobservacion);

            itemDestino.setRmestado(itemOrigen.isRmestado());
            Optional.ofNullable(itemOrigen.getRmrequerimiento()).ifPresent(itemDestino::setRmrequerimiento);
            Optional.ofNullable(itemOrigen.getRmobservacion()).ifPresent(itemDestino::setRmobservacion);

            itemDestino.setStestado(itemOrigen.isStestado());
            Optional.ofNullable(itemOrigen.getStrequerimiento()).ifPresent(itemDestino::setStrequerimiento);
            Optional.ofNullable(itemOrigen.getStobservacion()).ifPresent(itemDestino::setStobservacion);

            itemDestino.setPcestado(itemOrigen.isPcestado());
            Optional.ofNullable(itemOrigen.getPcrequerimiento()).ifPresent(itemDestino::setPcrequerimiento);
            Optional.ofNullable(itemOrigen.getPcobservacion()).ifPresent(itemDestino::setPcobservacion);
        });
    }

    @Transactional
    public void deletedById(Long id) {
        Recepcion recepcion = recepcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recepción no encontrada"));
        recepcion.setEliminado(true);
        recepcionRepository.save(recepcion);
    }

    @Transactional
    public Recepcion buscarPorId(Long id) {
        return recepcionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recepción no encontrada con id: " + id));
    }

}
