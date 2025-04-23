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
        return ensayoVH60Repository.save(ensayo);
    }

    @Transactional
    public EnsayoVh60 actualizarEnsayo(Long id, EnsayoVh60 ensayo) {
        EnsayoVh60 existente = ensayoVH60Repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensayo no encontrado con ID: " + id));

        // Copia todas las propiedades excepto el ID
        BeanUtils.copyProperties(ensayo, existente, "id");


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

