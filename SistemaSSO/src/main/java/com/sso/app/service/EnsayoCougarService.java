package com.sso.app.service;

import com.sso.app.entity.ensayo.EnsayoCougar;
import com.sso.app.repository.EnsayoCougarRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EnsayoCougarService {

    private final EnsayoCougarRepository ensayoCougarRepository;

    @Transactional
    public EnsayoCougar guardarEnsayo(EnsayoCougar ensayo) {
        // Asocia correctamente las imágenes antes de guardar
        if (ensayo.getImagenesCougar() != null) {
            ensayo.getImagenesCougar().forEach(img -> img.setEnsayoCougar(ensayo));
        }
        return ensayoCougarRepository.save(ensayo);
    }

    @Transactional
    public EnsayoCougar actualizarEnsayo(Long id, EnsayoCougar datosActualizados) {
        EnsayoCougar existente = ensayoCougarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo Cougar no encontrado con ID: " + id));

        // === RPM 100 ===
        existente.setRpm100CurrentF(datosActualizados.getRpm100CurrentF());
        existente.setRpm100VoltajeSalida(datosActualizados.getRpm100VoltajeSalida());
        existente.setRpm100CorrienteSalida(datosActualizados.getRpm100CorrienteSalida());
        existente.setRpm100PosicionSalida1(datosActualizados.getRpm100PosicionSalida1());
        existente.setRpm100PosicionSalida2(datosActualizados.getRpm100PosicionSalida2());
        existente.setRpm100TorqueFrenado(datosActualizados.getRpm100TorqueFrenado());
        existente.setRpm100TorqueFrenado1(datosActualizados.getRpm100TorqueFrenado1());
        existente.setRpm100TorqueFrenado2(datosActualizados.getRpm100TorqueFrenado2());
        existente.setRpm100TorqueFabricaReferencia(datosActualizados.getRpm100TorqueFabricaReferencia());
        existente.setRpm100TorqueReferencia1(datosActualizados.getRpm100TorqueReferencia1());
        existente.setRpm100TorqueReferencia2(datosActualizados.getRpm100TorqueReferencia2());
        existente.setRpm100TemperaturaCarcazaC(datosActualizados.getRpm100TemperaturaCarcazaC());

        // === RPM 200 ===
        existente.setRpm200CurrentF(datosActualizados.getRpm200CurrentF());
        existente.setRpm200VoltajeSalida(datosActualizados.getRpm200VoltajeSalida());
        existente.setRpm200CorrienteSalida(datosActualizados.getRpm200CorrienteSalida());
        existente.setRpm200PosicionSalida1(datosActualizados.getRpm200PosicionSalida1());
        existente.setRpm200PosicionSalida2(datosActualizados.getRpm200PosicionSalida2());
        existente.setRpm200TorqueFrenado(datosActualizados.getRpm200TorqueFrenado());
        existente.setRpm200TorqueFrenado1(datosActualizados.getRpm200TorqueFrenado1());
        existente.setRpm200TorqueFrenado2(datosActualizados.getRpm200TorqueFrenado2());
        existente.setRpm200TorqueFabricaReferencia(datosActualizados.getRpm200TorqueFabricaReferencia());
        existente.setRpm200TorqueReferencia1(datosActualizados.getRpm200TorqueReferencia1());
        existente.setRpm200TorqueReferencia2(datosActualizados.getRpm200TorqueReferencia2());
        existente.setRpm200TemperaturaCarcazaC(datosActualizados.getRpm200TemperaturaCarcazaC());

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
        existente.setPinturaOK(datosActualizados.isPinturaOK());
        existente.setPinturaObservacion(datosActualizados.getPinturaObservacion());

        // === Manejo seguro de imágenes ===
        existente.getImagenesCougar().clear();
        if (datosActualizados.getImagenesCougar() != null) {
            datosActualizados.getImagenesCougar().forEach(img -> {
                img.setEnsayoCougar(existente);
                existente.getImagenesCougar().add(img);
            });
        }

        return ensayoCougarRepository.save(existente);
    }

    public EnsayoCougar obtenerEnsayoPorId(Long id) {
        return ensayoCougarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo Cougar no encontrado con ID: " + id));
    }

    public List<EnsayoCougar> obtenerTodosEnsayos() {
        return StreamSupport.stream(ensayoCougarRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}