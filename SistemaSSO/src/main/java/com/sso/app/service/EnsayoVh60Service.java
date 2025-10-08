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

        // Actualizamos solo los campos simples (numéricos, booleanos, textos)
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

        // 🔄 Imágenes — forma segura
        existente.getImagenesVh60().clear();
        if (datosActualizados.getImagenesVh60() != null) {
            datosActualizados.getImagenesVh60().forEach(img -> {
                img.setEnsayoVh60(existente); // mantiene la relación bidireccional
                existente.getImagenesVh60().add(img);
            });
        }

        // otros campos (booleanos y textos)
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

