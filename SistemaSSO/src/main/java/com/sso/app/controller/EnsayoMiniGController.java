package com.sso.app.controller;

import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.service.EnsayoMiniGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ensayopminig")
public class EnsayoMiniGController {
    @Autowired
    private EnsayoMiniGService ensayoMiniGService;

    // Endpoint para crear una nuevo Ensayo
    @PostMapping
    public ResponseEntity<EnsayoMiniG> crearEnsayo(@RequestBody EnsayoMiniG ensayo) {

        EnsayoMiniG nuevaEnsayo = ensayoMiniGService.crearEnsayo(ensayo);
        return ResponseEntity.ok(nuevaEnsayo);
    }
    // Endpoint para actualizar un ensayo
    @PutMapping("/{id}")
    public ResponseEntity<EnsayoMiniG> actualizarEnsayo(
            @PathVariable Long id,
            @RequestBody EnsayoMiniG ensayo) {
        EnsayoMiniG ensayoActualizado = ensayoMiniGService.actualizarEnsayo(id, ensayo);
        return ResponseEntity.ok(ensayoActualizado);
    }

    // Endpoint para obtener un ensayo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EnsayoMiniG> obtenerEnsayoPorId(@PathVariable Long id) {
        EnsayoMiniG ensayo = ensayoMiniGService.obtenerEnsayoPorId(id);
        return ResponseEntity.ok(ensayo);
    }

    // Endpoint para obtener todas los ensayos
    @GetMapping
    public ResponseEntity<List<EnsayoMiniG>> obtenerTodosEnsayos() {
        List<EnsayoMiniG> ensayos = ensayoMiniGService.obtenerTodosEnsayos();
        return ResponseEntity.ok(ensayos);
    }
}
