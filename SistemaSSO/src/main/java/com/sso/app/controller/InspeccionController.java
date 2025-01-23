package com.sso.app.controller;

import com.sso.app.entity.Inspeccion;
import com.sso.app.service.InspeccionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/inspeccion")
@AllArgsConstructor
@CrossOrigin
public class InspeccionController {

    private final InspeccionService<Inspeccion> inspeccionService;

    // Endpoint para crear una nueva inspección
    @PostMapping
    public ResponseEntity<Inspeccion> crearInspeccion(@RequestBody Inspeccion inspeccion) {
        Inspeccion nuevaInspeccion = inspeccionService.crearInspeccion(inspeccion);
        return ResponseEntity.ok(nuevaInspeccion);
    }

    // Endpoint para actualizar una inspección existente
    @PutMapping("/{id}")
    public ResponseEntity<Inspeccion> actualizarInspeccion(@PathVariable Long id, @RequestBody Inspeccion inspeccion) {
        Inspeccion inspeccionActualizada = inspeccionService.actualizarInspeccion(id, inspeccion);
        return ResponseEntity.ok(inspeccionActualizada);
    }

    // Endpoint para obtener una inspección por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inspeccion> obtenerInspeccionPorId(@PathVariable Long id) {
        Inspeccion inspeccion = inspeccionService.obtenerInspeccionPorId(id);
        return ResponseEntity.ok(inspeccion);
    }

    // Endpoint para obtener todas las inspecciones
    @GetMapping
    public ResponseEntity<List<Inspeccion>> obtenerTodasInspecciones() {
        List<Inspeccion> inspecciones = inspeccionService.obtenerTodasInspecciones();
        return ResponseEntity.ok(inspecciones);
    }
}
