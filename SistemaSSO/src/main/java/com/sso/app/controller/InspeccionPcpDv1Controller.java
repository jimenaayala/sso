package com.sso.app.controller;

import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDv1;
import com.sso.app.service.InspeccionPcpDv1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspecciones/dv1")
public class InspeccionPcpDv1Controller {

    @Autowired
    private InspeccionPcpDv1Service inspeccionService;

    // Endpoint para crear una nueva inspección
    @PostMapping
    public ResponseEntity<InspeccionPcpDv1> crearInspeccion(@RequestBody InspeccionPcpDv1 inspeccion) {
        InspeccionPcpDv1 nuevaInspeccion = inspeccionService.crearInspeccion(inspeccion);
        return ResponseEntity.ok(nuevaInspeccion);
    }

    // Endpoint para actualizar una inspección existente
    @PutMapping("/{id}")
    public ResponseEntity<InspeccionPcpDv1> actualizarInspeccion(
            @PathVariable Long id,
            @RequestBody InspeccionPcpDv1 inspeccion) {
        InspeccionPcpDv1 inspeccionActualizada = inspeccionService.actualizarInspeccion(id, inspeccion);
        return ResponseEntity.ok(inspeccionActualizada);
    }

    // Endpoint para obtener una inspección por ID
    @GetMapping("/{id}")
    public ResponseEntity<InspeccionPcpDv1> obtenerInspeccionPorId(@PathVariable Long id) {
        InspeccionPcpDv1 inspeccion = inspeccionService.obtenerInspeccionPorId(id);
        return ResponseEntity.ok(inspeccion);
    }
    // Endpoint para obtener todas las inspecciones
    @GetMapping
    public ResponseEntity<List<InspeccionPcpDv1>> obtenerTodasInspecciones() {
        List<InspeccionPcpDv1> inspecciones = inspeccionService.obtenerTodasInspecciones();
        return ResponseEntity.ok(inspecciones);
    }
}
