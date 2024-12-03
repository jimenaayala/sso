package com.sso.app.controller;

import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import com.sso.app.service.InspeccionPcpVh60Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inspeccionesvh60")
public class InspeccionPcpVh60Controller {

    @Autowired
    private InspeccionPcpVh60Service inspeccionService;

    // Endpoint para crear una nueva inspección
    @PostMapping
    public ResponseEntity<InspeccionPcpVh60> crearInspeccion(@RequestBody InspeccionPcpVh60 inspeccion) {
        InspeccionPcpVh60 nuevaInspeccion = inspeccionService.crearInspeccion(inspeccion);
        return ResponseEntity.ok(nuevaInspeccion);
    }

    // Endpoint para actualizar una inspección existente
    @PutMapping("/{id}")
    public ResponseEntity<InspeccionPcpVh60> actualizarInspeccion(
            @PathVariable Long id,
            @RequestBody InspeccionPcpVh60 inspeccion) {
        InspeccionPcpVh60 inspeccionActualizada = inspeccionService.actualizarInspeccion(id, inspeccion);
        return ResponseEntity.ok(inspeccionActualizada);
    }

    // Endpoint para obtener una inspección por ID
    @GetMapping("/{id}")
    public ResponseEntity<InspeccionPcpVh60> obtenerInspeccionPorId(@PathVariable Long id) {
        InspeccionPcpVh60 inspeccion = inspeccionService.obtenerInspeccionPorId(id);
        return ResponseEntity.ok(inspeccion);
    }
    // Endpoint para obtener todas las inspecciones
    @GetMapping
    public ResponseEntity<List<InspeccionPcpVh60>> obtenerTodasInspecciones() {
        List<InspeccionPcpVh60> inspecciones = inspeccionService.obtenerTodasInspecciones();
        return ResponseEntity.ok(inspecciones);
    }
}
