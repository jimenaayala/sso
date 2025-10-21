package com.sso.app.service;

import com.sso.app.entity.UCLRecepcion.ItemRecepcionUCL;
import com.sso.app.entity.UCLRecepcion.RecepcionUCL;
import com.sso.app.repository.UCL.RecepcionUCLRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecepcionUCLService {

    private final RecepcionUCLRepository recepcionUCLRepository;

    @Transactional
    public RecepcionUCL guardarOActualizarRecepcionUCL(RecepcionUCL recepcionUCL) {
        if (recepcionUCL.getId() != null) {
            Optional<RecepcionUCL> existenteOpt = recepcionUCLRepository.findById(recepcionUCL.getId());

            if (existenteOpt.isPresent()) {
                RecepcionUCL existente = existenteOpt.get();
                actualizarCampos(existente, recepcionUCL);
                return recepcionUCLRepository.save(existente);
            }
        }
        return recepcionUCLRepository.save(recepcionUCL);
    }

    @Transactional
    private void actualizarCampos(RecepcionUCL destino, RecepcionUCL origen) {
        Optional.ofNullable(origen.getComentario()).ifPresent(destino::setComentario);
        if (origen.isEliminado()) {
            destino.setEliminado(true);
        }

        Optional.ofNullable(origen.getItemRecepcionUCL()).ifPresent(itemOrigen -> {
            ItemRecepcionUCL itemDestino = destino.getItemRecepcionUCL();

            itemDestino.setTensoresEstado(itemOrigen.isTensoresEstado());
            Optional.ofNullable(itemOrigen.getTensoresRequerimiento()).ifPresent(itemDestino::setTensoresRequerimiento);
            Optional.ofNullable(itemOrigen.getTensoresObservacion()).ifPresent(itemDestino::setTensoresObservacion);

            itemDestino.setBalconFrontalEstado(itemOrigen.isBalconFrontalEstado());
            Optional.ofNullable(itemOrigen.getBalconFrontalRequerimiento()).ifPresent(itemDestino::setBalconFrontalRequerimiento);
            Optional.ofNullable(itemOrigen.getBalconFrontalObservacion()).ifPresent(itemDestino::setBalconFrontalObservacion);

            itemDestino.setBalconSuperiorEstado(itemOrigen.isBalconSuperiorEstado());
            Optional.ofNullable(itemOrigen.getBalconSuperiorRequerimiento()).ifPresent(itemDestino::setBalconSuperiorRequerimiento);
            Optional.ofNullable(itemOrigen.getBalconSuperiorObservacion()).ifPresent(itemDestino::setBalconSuperiorObservacion);

            itemDestino.setCapotCubreRodilloEstado(itemOrigen.isCapotCubreRodilloEstado());
            Optional.ofNullable(itemOrigen.getCapotCubreRodilloRequerimiento()).ifPresent(itemDestino::setCapotCubreRodilloRequerimiento);
            Optional.ofNullable(itemOrigen.getCapotCubreRodilloObservacion()).ifPresent(itemDestino::setCapotCubreRodilloObservacion);

            itemDestino.setCubrePoleasEstado(itemOrigen.isCubrePoleasEstado());
            Optional.ofNullable(itemOrigen.getCubrePoleasRequerimiento()).ifPresent(itemDestino::setCubrePoleasRequerimiento);
            Optional.ofNullable(itemOrigen.getCubrePoleasObservacion()).ifPresent(itemDestino::setCubrePoleasObservacion);

            itemDestino.setCubreDiscoEstado(itemOrigen.isCubreDiscoEstado());
            Optional.ofNullable(itemOrigen.getCubreDiscoRequerimiento()).ifPresent(itemDestino::setCubreDiscoRequerimiento);
            Optional.ofNullable(itemOrigen.getCubreDiscoObservacion()).ifPresent(itemDestino::setCubreDiscoObservacion);

            itemDestino.setSoporteMotorReguladorEstado(itemOrigen.isSoporteMotorReguladorEstado());
            Optional.ofNullable(itemOrigen.getSoporteMotorReguladorRequerimiento()).ifPresent(itemDestino::setSoporteMotorReguladorRequerimiento);
            Optional.ofNullable(itemOrigen.getSoporteMotorReguladorObservacion()).ifPresent(itemDestino::setSoporteMotorReguladorObservacion);

            itemDestino.setCrucetaEstroboEstado(itemOrigen.isCrucetaEstroboEstado());
            Optional.ofNullable(itemOrigen.getCrucetaEstroboRequerimiento()).ifPresent(itemDestino::setCrucetaEstroboRequerimiento);
            Optional.ofNullable(itemOrigen.getCrucetaEstroboObservacion()).ifPresent(itemDestino::setCrucetaEstroboObservacion);

            itemDestino.setPlacasContrapesosEstado(itemOrigen.isPlacasContrapesosEstado());
            Optional.ofNullable(itemOrigen.getPlacasContrapesosRequerimiento()).ifPresent(itemDestino::setPlacasContrapesosRequerimiento);
            Optional.ofNullable(itemOrigen.getPlacasContrapesosObservacion()).ifPresent(itemDestino::setPlacasContrapesosObservacion);

            itemDestino.setPoleaConducidaEstado(itemOrigen.isPoleaConducidaEstado());
            Optional.ofNullable(itemOrigen.getPoleaConducidaRequerimiento()).ifPresent(itemDestino::setPoleaConducidaRequerimiento);
            Optional.ofNullable(itemOrigen.getPoleaConducidaObservacion()).ifPresent(itemDestino::setPoleaConducidaObservacion);

            itemDestino.setSpeedSentryEstado(itemOrigen.isSpeedSentryEstado());
            Optional.ofNullable(itemOrigen.getSpeedSentryRequerimiento()).ifPresent(itemDestino::setSpeedSentryRequerimiento);
            Optional.ofNullable(itemOrigen.getSpeedSentryObservacion()).ifPresent(itemDestino::setSpeedSentryObservacion);

            Optional.ofNullable(itemOrigen.getFaltantesAdicionales()).ifPresent(itemDestino::setFaltantesAdicionales);
        });
    }

    @Transactional
    public void deletedById(Long id) {
        RecepcionUCL recepcion = recepcionUCLRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recepción UCL no encontrada"));
        recepcion.setEliminado(true);
        recepcionUCLRepository.save(recepcion);
    }

    @Transactional
    public RecepcionUCL buscarPorId(Long id) {
        return recepcionUCLRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recepción UCL no encontrada con id: " + id));
    }
}
