package com.sso.app.service;

import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.repository.EnsayoMiniGRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor
public class EnsayoMiniGService {

    private final EnsayoMiniGRepository ensayoMiniGRepository;

    @Transactional
    public EnsayoMiniG guardarEnsayo(EnsayoMiniG ensayo) {
        if (ensayo.getImagenesMiniG() != null) {
            ensayo.getImagenesMiniG().forEach(img -> img.setEnsayoMiniG(ensayo));
        }
        return ensayoMiniGRepository.save(ensayo);
    }

    @Transactional
    public EnsayoMiniG actualizarEnsayo(Long id, EnsayoMiniG datosActualizados) {
        EnsayoMiniG existente = ensayoMiniGRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo MiniG no encontrado con ID: " + id));

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

        // === RPM 300 ===
        existente.setRpm300CurrentF(datosActualizados.getRpm300CurrentF());
        existente.setRpm300VoltajeSalida(datosActualizados.getRpm300VoltajeSalida());
        existente.setRpm300CorrienteSalida(datosActualizados.getRpm300CorrienteSalida());
        existente.setRpm300PosicionSalida1(datosActualizados.getRpm300PosicionSalida1());
        existente.setRpm300PosicionSalida2(datosActualizados.getRpm300PosicionSalida2());
        existente.setRpm300TorqueFrenado(datosActualizados.getRpm300TorqueFrenado());
        existente.setRpm300TorqueFrenado1(datosActualizados.getRpm300TorqueFrenado1());
        existente.setRpm300TorqueFrenado2(datosActualizados.getRpm300TorqueFrenado2());
        existente.setRpm300TorqueFabricaReferencia(datosActualizados.getRpm300TorqueFabricaReferencia());
        existente.setRpm300TorqueReferencia1(datosActualizados.getRpm300TorqueReferencia1());
        existente.setRpm300TorqueReferencia2(datosActualizados.getRpm300TorqueReferencia2());
        existente.setRpm300TemperaturaCarcazaC(datosActualizados.getRpm300TemperaturaCarcazaC());

        // === RPM 400 ===
        existente.setRpm400CurrentF(datosActualizados.getRpm400CurrentF());
        existente.setRpm400VoltajeSalida(datosActualizados.getRpm400VoltajeSalida());
        existente.setRpm400CorrienteSalida(datosActualizados.getRpm400CorrienteSalida());
        existente.setRpm400PosicionSalida1(datosActualizados.getRpm400PosicionSalida1());
        existente.setRpm400PosicionSalida2(datosActualizados.getRpm400PosicionSalida2());
        existente.setRpm400TorqueFrenado(datosActualizados.getRpm400TorqueFrenado());
        existente.setRpm400TorqueFrenado1(datosActualizados.getRpm400TorqueFrenado1());
        existente.setRpm400TorqueFrenado2(datosActualizados.getRpm400TorqueFrenado2());
        existente.setRpm400TorqueFabricaReferencia(datosActualizados.getRpm400TorqueFabricaReferencia());
        existente.setRpm400TorqueReferencia1(datosActualizados.getRpm400TorqueReferencia1());
        existente.setRpm400TorqueReferencia2(datosActualizados.getRpm400TorqueReferencia2());
        existente.setRpm400TemperaturaCarcazaC(datosActualizados.getRpm400TemperaturaCarcazaC());

        // === RPM 500 ===
        existente.setRpm500CurrentF(datosActualizados.getRpm500CurrentF());
        existente.setRpm500VoltajeSalida(datosActualizados.getRpm500VoltajeSalida());
        existente.setRpm500CorrienteSalida(datosActualizados.getRpm500CorrienteSalida());
        existente.setRpm500PosicionSalida1(datosActualizados.getRpm500PosicionSalida1());
        existente.setRpm500PosicionSalida2(datosActualizados.getRpm500PosicionSalida2());
        existente.setRpm500TorqueFrenado(datosActualizados.getRpm500TorqueFrenado());
        existente.setRpm500TorqueFrenado1(datosActualizados.getRpm500TorqueFrenado1());
        existente.setRpm500TorqueFrenado2(datosActualizados.getRpm500TorqueFrenado2());
        existente.setRpm500TorqueFabricaReferencia(datosActualizados.getRpm500TorqueFabricaReferencia());
        existente.setRpm500TorqueReferencia1(datosActualizados.getRpm500TorqueReferencia1());
        existente.setRpm500TorqueReferencia2(datosActualizados.getRpm500TorqueReferencia2());
        existente.setRpm500TemperaturaCarcazaC(datosActualizados.getRpm500TemperaturaCarcazaC());

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

        // === Manejo de imágenes ===
        existente.getImagenesMiniG().clear();
        if (datosActualizados.getImagenesMiniG() != null) {
            datosActualizados.getImagenesMiniG().forEach(img -> {
                img.setEnsayoMiniG(existente);
                existente.getImagenesMiniG().add(img);
            });
        }

        return ensayoMiniGRepository.save(existente);
    }

    public EnsayoMiniG obtenerEnsayoPorId(Long id) {
        return ensayoMiniGRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo MiniG no encontrado con ID: " + id));
    }

    public List<EnsayoMiniG> obtenerTodosEnsayos() {
        return StreamSupport.stream(ensayoMiniGRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}




