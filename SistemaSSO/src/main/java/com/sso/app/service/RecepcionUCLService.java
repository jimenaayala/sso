package com.sso.app.service;

import com.sso.app.entity.UCLRecepcion.ItemRecepcionUCL;
import com.sso.app.entity.UCLRecepcion.RecepcionUCL;
import com.sso.app.repository.UCL.RecepcionUCLRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
@AllArgsConstructor
public class RecepcionUCLService {

    private final RecepcionUCLRepository recepcionUCLRepository;

    @Transactional
    public RecepcionUCL guardarOActualizarRecepcionUCL(RecepcionUCL recepcionUCL) {

        if (recepcionUCL.getId() != null) {
            RecepcionUCL existente = recepcionUCLRepository.findById(recepcionUCL.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Recepción UCL no encontrada"));

            actualizarCampos(existente, recepcionUCL);
            return recepcionUCLRepository.save(existente);
        }

        return recepcionUCLRepository.save(recepcionUCL);
    }

    private void actualizarCampos(RecepcionUCL destino, RecepcionUCL origen) {

        if (origen.getComentario() != null) {
            destino.setComentario(origen.getComentario());
        }

        if (origen.isEliminado()) {
            destino.setEliminado(true);
        }

        if (origen.getItemRecepcionUCL() != null) {
            actualizarItem(destino.getItemRecepcionUCL(), origen.getItemRecepcionUCL());
        }
    }

    private void actualizarItem(ItemRecepcionUCL destino, ItemRecepcionUCL origen) {
        if (destino == null) {
            destino = new ItemRecepcionUCL();
        }

        destino.setTensoresEstado(origen.isTensoresEstado());
        destino.setTensoresRequerimiento(origen.getTensoresRequerimiento());
        destino.setTensoresObservacion(origen.getTensoresObservacion());

        destino.setBalconFrontalEstado(origen.isBalconFrontalEstado());
        destino.setBalconFrontalRequerimiento(origen.getBalconFrontalRequerimiento());
        destino.setBalconFrontalObservacion(origen.getBalconFrontalObservacion());

        destino.setBalconSuperiorEstado(origen.isBalconSuperiorEstado());
        destino.setBalconSuperiorRequerimiento(origen.getBalconSuperiorRequerimiento());
        destino.setBalconSuperiorObservacion(origen.getBalconSuperiorObservacion());

        destino.setCapotCubreRodilloEstado(origen.isCapotCubreRodilloEstado());
        destino.setCapotCubreRodilloRequerimiento(origen.getCapotCubreRodilloRequerimiento());
        destino.setCapotCubreRodilloObservacion(origen.getCapotCubreRodilloObservacion());

        destino.setCubrePoleasEstado(origen.isCubrePoleasEstado());
        destino.setCubrePoleasRequerimiento(origen.getCubrePoleasRequerimiento());
        destino.setCubrePoleasObservacion(origen.getCubrePoleasObservacion());

        destino.setCubreDiscoEstado(origen.isCubreDiscoEstado());
        destino.setCubreDiscoRequerimiento(origen.getCubreDiscoRequerimiento());
        destino.setCubreDiscoObservacion(origen.getCubreDiscoObservacion());

        destino.setSoporteMotorReguladorEstado(origen.isSoporteMotorReguladorEstado());
        destino.setSoporteMotorReguladorRequerimiento(origen.getSoporteMotorReguladorRequerimiento());
        destino.setSoporteMotorReguladorObservacion(origen.getSoporteMotorReguladorObservacion());

        destino.setCrucetaEstroboEstado(origen.isCrucetaEstroboEstado());
        destino.setCrucetaEstroboRequerimiento(origen.getCrucetaEstroboRequerimiento());
        destino.setCrucetaEstroboObservacion(origen.getCrucetaEstroboObservacion());

        destino.setPlacasContrapesosEstado(origen.isPlacasContrapesosEstado());
        destino.setPlacasContrapesosRequerimiento(origen.getPlacasContrapesosRequerimiento());
        destino.setPlacasContrapesosObservacion(origen.getPlacasContrapesosObservacion());

        destino.setPoleaConducidaEstado(origen.isPoleaConducidaEstado());
        destino.setPoleaConducidaRequerimiento(origen.getPoleaConducidaRequerimiento());
        destino.setPoleaConducidaObservacion(origen.getPoleaConducidaObservacion());

        destino.setSpeedSentryEstado(origen.isSpeedSentryEstado());
        destino.setSpeedSentryRequerimiento(origen.getSpeedSentryRequerimiento());
        destino.setSpeedSentryObservacion(origen.getSpeedSentryObservacion());

        destino.setFaltantesAdicionales(origen.getFaltantesAdicionales());

        // 🔹 MUY IMPORTANTE: volver a asignarlo por si era null
        // (porque no es bidireccional)
    }

    @Transactional
    public void deletedById(Long id) {
        RecepcionUCL recepcion = recepcionUCLRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recepción UCL no encontrada"));
        recepcion.setEliminado(true);
    }

    @Transactional
    public RecepcionUCL buscarPorId(Long id) {
        return recepcionUCLRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recepción UCL no encontrada"));
    }
}