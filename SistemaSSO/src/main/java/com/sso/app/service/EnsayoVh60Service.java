package com.sso.app.service;


import com.sso.app.entity.ensayo.EnsayoVh60;
import com.sso.app.repository.EnsayoVH60Repository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor
public class EnsayoVh60Service {

    private final EnsayoVH60Repository ensayoVH60Repository;

    @Transactional
    public EnsayoVh60 guardarEnsayo(EnsayoVh60 ensayo) {
        // Asocia correctamente las imágenes antes de guardar
        if (ensayo.getImagenesVh60() != null) {
            ensayo.getImagenesVh60().forEach(img -> img.setEnsayoVh60(ensayo));
        }
        return ensayoVH60Repository.save(ensayo);
    }

    @Transactional
    public EnsayoVh60 actualizarEnsayo(Long id, EnsayoVh60 datosActualizados) {
        EnsayoVh60 existente = ensayoVH60Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo no encontrado con ID: " + id));

        // ===============================
        // 🔧 RPM 100
        // ===============================
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

        // ===============================
        // 🔧 RPM 200
        // ===============================
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

        // ===============================
        // 🔧 RPM 300
        // ===============================
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

        // ===============================
        // 🧱 Campos booleanos + observaciones
        // ===============================
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

        // ===============================
        // 🖼️ Manejo de imágenes bidireccional
        // ===============================
        existente.getImagenesVh60().clear();
        if (datosActualizados.getImagenesVh60() != null) {
            datosActualizados.getImagenesVh60().forEach(img -> {
                img.setEnsayoVh60(existente);
                existente.getImagenesVh60().add(img);
            });
        }

        return ensayoVH60Repository.save(existente);
    }

    public EnsayoVh60 obtenerEnsayoPorId(Long id) {
        return ensayoVH60Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo no encontrado con ID: " + id));
    }

    public List<EnsayoVh60> obtenerTodosEnsayos() {
        return StreamSupport.stream(ensayoVH60Repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}

