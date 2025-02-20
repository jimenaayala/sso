package com.sso.app.service;

import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import com.sso.app.repository.EnsayoMiniGRepository;
import com.sso.app.repository.EnsayoRepository;
import com.sso.app.repository.InspeccionPcpMiniGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnsayoMiniGService {
    @Autowired
    private EnsayoMiniGRepository ensayoMiniGRepository;

    // Crear un nuevo ensayo con entidades relacionadas
    @Transactional
    public EnsayoMiniG crearEnsayo(EnsayoMiniG ensayo) {
        // Al guardar inspección, también se guardan las entidades relacionadas gracias a CascadeType.ALL
        return this.ensayoMiniGRepository.save(ensayo);
    }

    // Actualizar un ensayo existente
    @Transactional
    public EnsayoMiniG actualizarEnsayo(Long id, EnsayoMiniG ensayoNuevo) {
        Optional<EnsayoMiniG> EnsayoExistente = ensayoMiniGRepository.findById(id);

        if (EnsayoExistente.isPresent()) {
            EnsayoMiniG ensayo = EnsayoExistente.get();

            // Actualizamos cada entidad relacionada
            ensayo.setRpm200(ensayoNuevo.getRpm200());
            ensayo.setRpm300(ensayoNuevo.getRpm300());
            ensayo.setRpm400(ensayoNuevo.getRpm400());
            ensayo.setRpm500(ensayoNuevo.getRpm500());
            ensayo.setNivelDeAceite(ensayoNuevo.getNivelDeAceite());
            ensayo.setNivelDeRuido(ensayoNuevo.getNivelDeRuido());
            ensayo.setTemperatura(ensayoNuevo.getTemperatura());
            ensayo.setCargaAxial(ensayoNuevo.getCargaAxial());
            ensayo.setPintura(ensayoNuevo.getPintura());
            ensayo.setFugaDeAceite(ensayoNuevo.getFugaDeAceite());
            ensayo.setNivelDeVibracion(ensayoNuevo.getNivelDeVibracion());

            return  ensayoMiniGRepository.save(ensayo);
        } else {
            throw new RuntimeException("Ensayo no encontrado con ID: " + id);
        }
    }

    // Obtener ensayo por ID
    public EnsayoMiniG obtenerEnsayoPorId(Long id) {
        return ensayoMiniGRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo no encontrado con ID: " + id));
    }

    // Obtener todas los ensayos
    public List<EnsayoMiniG> obtenerTodosEnsayos() {
        return (List<EnsayoMiniG>) this.ensayoMiniGRepository.findAll();
    }
}




