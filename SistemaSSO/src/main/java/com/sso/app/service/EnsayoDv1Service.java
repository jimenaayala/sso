package com.sso.app.service;

import com.sso.app.entity.ensayo.EnsayoDv1;
import com.sso.app.repository.EnsayoDv1Repository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EnsayoDv1Service {

    private final EnsayoDv1Repository ensayoDv1Repository;

    @Transactional
    public EnsayoDv1 guardarEnsayo(EnsayoDv1 ensayo) {
        if (ensayo.getImagenesDv1() != null) {
            ensayo.getImagenesDv1().forEach(img -> img.setEnsayoDv1(ensayo));
        }
        return ensayoDv1Repository.save(ensayo);
    }

    @Transactional
    public EnsayoDv1 actualizarEnsayo(Long id, EnsayoDv1 datosActualizados) {
        EnsayoDv1 existente = ensayoDv1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo DV1 no encontrado con ID: " + id));

        // === Presión 10 ===
        existente.setPresion10CurrentF(datosActualizados.getPresion10CurrentF());
        existente.setPresion10VoltajeSalida(datosActualizados.getPresion10VoltajeSalida());
        existente.setPresion10CorrienteSalida(datosActualizados.getPresion10CorrienteSalida());
        existente.setPresion10PosicionSalida1(datosActualizados.getPresion10PosicionSalida1());
        existente.setPresion10PosicionSalida2(datosActualizados.getPresion10PosicionSalida2());
        existente.setPresion10TorqueFrenado(datosActualizados.getPresion10TorqueFrenado());
        existente.setPresion10TorqueFrenado1(datosActualizados.getPresion10TorqueFrenado1());
        existente.setPresion10TorqueFrenado2(datosActualizados.getPresion10TorqueFrenado2());
        existente.setPresion10TorqueFabricaReferencia(datosActualizados.getPresion10TorqueFabricaReferencia());
        existente.setPresion10TorqueReferencia1(datosActualizados.getPresion10TorqueReferencia1());
        existente.setPresion10TorqueReferencia2(datosActualizados.getPresion10TorqueReferencia2());
        existente.setPresion10TemperaturaCarcazaC(datosActualizados.getPresion10TemperaturaCarcazaC());

        // === Presión 20 ===
        existente.setPresion20CurrentF(datosActualizados.getPresion20CurrentF());
        existente.setPresion20VoltajeSalida(datosActualizados.getPresion20VoltajeSalida());
        existente.setPresion20CorrienteSalida(datosActualizados.getPresion20CorrienteSalida());
        existente.setPresion20PosicionSalida1(datosActualizados.getPresion20PosicionSalida1());
        existente.setPresion20PosicionSalida2(datosActualizados.getPresion20PosicionSalida2());
        existente.setPresion20TorqueFrenado(datosActualizados.getPresion20TorqueFrenado());
        existente.setPresion20TorqueFrenado1(datosActualizados.getPresion20TorqueFrenado1());
        existente.setPresion20TorqueFrenado2(datosActualizados.getPresion20TorqueFrenado2());
        existente.setPresion20TorqueFabricaReferencia(datosActualizados.getPresion20TorqueFabricaReferencia());
        existente.setPresion20TorqueReferencia1(datosActualizados.getPresion20TorqueReferencia1());
        existente.setPresion20TorqueReferencia2(datosActualizados.getPresion20TorqueReferencia2());
        existente.setPresion20TemperaturaCarcazaC(datosActualizados.getPresion20TemperaturaCarcazaC());

        // === Presión 70 ===
        existente.setPresion70CurrentF(datosActualizados.getPresion70CurrentF());
        existente.setPresion70VoltajeSalida(datosActualizados.getPresion70VoltajeSalida());
        existente.setPresion70CorrienteSalida(datosActualizados.getPresion70CorrienteSalida());
        existente.setPresion70PosicionSalida1(datosActualizados.getPresion70PosicionSalida1());
        existente.setPresion70PosicionSalida2(datosActualizados.getPresion70PosicionSalida2());
        existente.setPresion70TorqueFrenado(datosActualizados.getPresion70TorqueFrenado());
        existente.setPresion70TorqueFrenado1(datosActualizados.getPresion70TorqueFrenado1());
        existente.setPresion70TorqueFrenado2(datosActualizados.getPresion70TorqueFrenado2());
        existente.setPresion70TorqueFabricaReferencia(datosActualizados.getPresion70TorqueFabricaReferencia());
        existente.setPresion70TorqueReferencia1(datosActualizados.getPresion70TorqueReferencia1());
        existente.setPresion70TorqueReferencia2(datosActualizados.getPresion70TorqueReferencia2());
        existente.setPresion70TemperaturaCarcazaC(datosActualizados.getPresion70TemperaturaCarcazaC());

        // === Presión 100 ===
        existente.setPresion100CurrentF(datosActualizados.getPresion100CurrentF());
        existente.setPresion100VoltajeSalida(datosActualizados.getPresion100VoltajeSalida());
        existente.setPresion100CorrienteSalida(datosActualizados.getPresion100CorrienteSalida());
        existente.setPresion100PosicionSalida1(datosActualizados.getPresion100PosicionSalida1());
        existente.setPresion100PosicionSalida2(datosActualizados.getPresion100PosicionSalida2());
        existente.setPresion100TorqueFrenado(datosActualizados.getPresion100TorqueFrenado());
        existente.setPresion100TorqueFrenado1(datosActualizados.getPresion100TorqueFrenado1());
        existente.setPresion100TorqueFrenado2(datosActualizados.getPresion100TorqueFrenado2());
        existente.setPresion100TorqueFabricaReferencia(datosActualizados.getPresion100TorqueFabricaReferencia());
        existente.setPresion100TorqueReferencia1(datosActualizados.getPresion100TorqueReferencia1());
        existente.setPresion100TorqueReferencia2(datosActualizados.getPresion100TorqueReferencia2());
        existente.setPresion100TemperaturaCarcazaC(datosActualizados.getPresion100TemperaturaCarcazaC());

        // === Estado General ===
        existente.setCargaAxialOK(datosActualizados.isCargaAxialOK());
        existente.setCargaAxialObservacion(datosActualizados.getCargaAxialObservacion());
        existente.setTemperaturaOK(datosActualizados.isTemperaturaOK());
        existente.setTemperaturaObservacion(datosActualizados.getTemperaturaObservacion());
        existente.setNivelDeRuidoOK(datosActualizados.isNivelDeRuidoOK());
        existente.setNivelDeRuidoObservacion(datosActualizados.getNivelDeRuidoObservacion());
        existente.setNivelDeVibracionOK(datosActualizados.isNivelDeVibracionOK());
        existente.setNivelDeVibracionObservacion(datosActualizados.getNivelDeVibracionObservacion());
        existente.setFugaDeAceiteOK(datosActualizados.isFugaDeAceiteOK());
        existente.setFugaDeAceiteObservacion(datosActualizados.getFugaDeAceiteObservacion());
        existente.setNivelDeAceiteOK(datosActualizados.isNivelDeAceiteOK());
        existente.setNivelDeAceiteObservacion(datosActualizados.getNivelDeAceiteObservacion());
        existente.setPinturaOK(datosActualizados.isPinturaOK());
        existente.setPinturaObservacion(datosActualizados.getPinturaObservacion());

        // === Manejo seguro de imágenes ===
        existente.getImagenesDv1().clear();
        if (datosActualizados.getImagenesDv1() != null) {
            datosActualizados.getImagenesDv1().forEach(img -> {
                img.setEnsayoDv1(existente);
                existente.getImagenesDv1().add(img);
            });
        }

        return ensayoDv1Repository.save(existente);
    }

    public EnsayoDv1 obtenerEnsayoPorId(Long id) {
        return ensayoDv1Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo DV1 no encontrado con ID: " + id));
    }

    public List<EnsayoDv1> obtenerTodosEnsayos() {
        return StreamSupport.stream(ensayoDv1Repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
