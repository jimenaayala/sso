package com.sso.app.service;

import com.sso.app.entity.ensayo.EnsayoCougar;
import com.sso.app.entity.ensayo.EnsayoDv1;
import com.sso.app.repository.EnsayoCougarRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnsayoCougarService {
    private final EnsayoCougarRepository ensayoCougarRepository;

    @Transactional
    public EnsayoCougar guardarEnsayo(EnsayoCougar ensayoCougar){
        return this.ensayoCougarRepository.save(ensayoCougar);
    }

    @Transactional
    public EnsayoCougar actualizarEnsayo(Long id, EnsayoCougar ensayo){
        Optional<EnsayoCougar> ensayoExistente = ensayoCougarRepository.findById(id);

        if (ensayoExistente.isPresent()){
            EnsayoCougar ensayoCougar = ensayoExistente.get();

            ensayoCougar.setCargaAxial(ensayo.getCargaAxial());
            ensayoCougar.setPintura(ensayo.getPintura());
            ensayoCougar.setFugaDeAceite(ensayo.getFugaDeAceite());
            ensayoCougar.setTemperatura(ensayo.getTemperatura());
            ensayoCougar.setNivelDeRuido(ensayo.getNivelDeRuido());
            ensayoCougar.setNivelDeVibracion(ensayo.getNivelDeVibracion());

            ensayoCougar.setRpm100(ensayo.getRpm100());
            ensayoCougar.setRpm200(ensayo.getRpm200());

            return this.ensayoCougarRepository.save(ensayoCougar);
        }else {
            throw new RuntimeException("Ensayo no encontrado con ID: " + id);
        }
    }

    // Obtener ensayo por ID
    public EnsayoCougar obtenerEnsayoPorId(Long id) {
        return ensayoCougarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo no encontrado con ID: " + id));
    }

    // Obtener todas los ensayos
    public List<EnsayoCougar> obtenerTodosEnsayos() {
        return (List<EnsayoCougar>) this.ensayoCougarRepository.findAll();
    }
}
