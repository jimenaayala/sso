package com.sso.app.controller;

import com.sso.app.entity.ensayo.EnsayoCougar;
import com.sso.app.entity.ensayo.EnsayoDv1;
import com.sso.app.service.EnsayoCougarService;
import com.sso.app.service.EnsayoDv1Service;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ensayos/cougar")
@AllArgsConstructor
public class EnsayoCougarController {
    private final EnsayoCougarService ensayoCougarService;



    // Endpoint para crear una nuevo Ensayo
    @PostMapping
    public ResponseEntity<EnsayoCougar> crearEnsayo(@RequestBody EnsayoCougar ensayo) {

        EnsayoCougar nuevoEnsayo = this.ensayoCougarService.guardarEnsayo(ensayo);
        return ResponseEntity.ok(nuevoEnsayo);
    }
    // Endpoint para actualizar un ensayo
    @PutMapping("/{id}")
    public ResponseEntity<EnsayoCougar> actualizarEnsayo(
            @PathVariable Long id,
            @RequestBody EnsayoCougar ensayo) {
        EnsayoCougar ensayoActualizado = this.ensayoCougarService.actualizarEnsayo(id, ensayo);
        return ResponseEntity.ok(ensayoActualizado);
    }

    // Endpoint para obtener un ensayo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EnsayoCougar> obtenerEnsayoPorId(@PathVariable Long id) {
        EnsayoCougar ensayo = ensayoCougarService.obtenerEnsayoPorId(id);
        return ResponseEntity.ok(ensayo);
    }

    // Endpoint para obtener todas los ensayos
    @GetMapping
    public ResponseEntity<List<EnsayoCougar>> obtenerTodosEnsayos() {
        List<EnsayoCougar> ensayos = ensayoCougarService.obtenerTodosEnsayos();
        return ResponseEntity.ok(ensayos);
    }
}
