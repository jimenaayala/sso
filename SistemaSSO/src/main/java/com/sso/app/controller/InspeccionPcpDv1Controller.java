package com.sso.app.controller;

import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDV1;
import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import com.sso.app.service.InspeccionPcpDv1Service;
import com.sso.app.service.InspeccionPcpVh60Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspeccionesdv1")
public class InspeccionPcpDv1Controller {

    @Autowired
    private InspeccionPcpDv1Service inspeccionService;

    // Endpoint para crear una nueva inspección
    @PostMapping
    public ResponseEntity<InspeccionPcpDV1> crearInspeccion(@RequestBody InspeccionPcpDV1 inspeccion) {
        InspeccionPcpDV1 nuevaInspeccion = inspeccionService.crearInspeccion(inspeccion);
        return ResponseEntity.ok(nuevaInspeccion);
    }

    // Endpoint para actualizar una inspección existente
    @PutMapping("/{id}")
    public ResponseEntity<InspeccionPcpDV1> actualizarInspeccion(
            @PathVariable Long id,
            @RequestBody InspeccionPcpDV1 inspeccion) {
        InspeccionPcpDV1 inspeccionActualizada = inspeccionService.actualizarInspeccion(id, inspeccion);
        return ResponseEntity.ok(inspeccionActualizada);
    }

    // Endpoint para obtener una inspección por ID
    @GetMapping("/{id}")
    public ResponseEntity<InspeccionPcpDV1> obtenerInspeccionPorId(@PathVariable Long id) {
        InspeccionPcpDV1 inspeccion = inspeccionService.obtenerInspeccionPorId(id);
        return ResponseEntity.ok(inspeccion);
    }
    // Endpoint para obtener todas las inspecciones
    @GetMapping
    public ResponseEntity<List<InspeccionPcpDV1>> obtenerTodasInspecciones() {
        List<InspeccionPcpDV1> inspecciones = inspeccionService.obtenerTodasInspecciones();
        return ResponseEntity.ok(inspecciones);
    }
}
