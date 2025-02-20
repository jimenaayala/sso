package com.sso.app.controller;

import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.entity.ensayo.EnsayoVh60;
import com.sso.app.service.EnsayoVh60Service;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ensayopvh60")
@AllArgsConstructor
public class EnsayoVh60Controller {
    private final EnsayoVh60Service ensayoVh60Service;

    // Endpoint para crear una nuevo Ensayo
    @PostMapping
    public ResponseEntity<EnsayoVh60> crearEnsayo(@RequestBody EnsayoVh60 ensayo) {

        EnsayoVh60 nuevaEnsayo = ensayoVh60Service.guardarEnsayo(ensayo);
        return ResponseEntity.ok(nuevaEnsayo);
    }
    // Endpoint para actualizar un ensayo
    @PutMapping("/{id}")
    public ResponseEntity<EnsayoVh60> actualizarEnsayo(
            @PathVariable Long id,
            @RequestBody EnsayoVh60 ensayo) {
        EnsayoVh60 ensayoActualizado = ensayoVh60Service.actualizarEnsayo(id, ensayo);
        return ResponseEntity.ok(ensayoActualizado);
    }

    // Endpoint para obtener un ensayo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EnsayoVh60> obtenerEnsayoPorId(@PathVariable Long id) {
        EnsayoVh60 ensayo = ensayoVh60Service.obtenerEnsayoPorId(id);
        return ResponseEntity.ok(ensayo);
    }

    // Endpoint para obtener todas los ensayos
    @GetMapping
    public ResponseEntity<List<EnsayoVh60>> obtenerTodosEnsayos() {
        List<EnsayoVh60> ensayos = ensayoVh60Service.obtenerTodosEnsayos();
        return ResponseEntity.ok(ensayos);
    }
}
