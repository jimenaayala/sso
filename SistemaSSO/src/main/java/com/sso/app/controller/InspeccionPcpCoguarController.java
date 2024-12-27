package com.sso.app.controller;

import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import com.sso.app.service.InspeccionPcpCoguarService;
import com.sso.app.service.InspeccionPcpVh60Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inspeccionescoguar")
public class InspeccionPcpCoguarController {
    @Autowired
    private InspeccionPcpCoguarService inspeccionService;

    // Endpoint para crear una nueva inspección
    @PostMapping
    public ResponseEntity<InspeccionPcpCougar> crearInspeccion(@RequestBody InspeccionPcpCougar inspeccion) {
        InspeccionPcpCougar nuevaInspeccion = inspeccionService.crearInspeccion(inspeccion);
        return ResponseEntity.ok(nuevaInspeccion);
    }

    // Endpoint para actualizar una inspección existente
    @PutMapping("/{id}")
    public ResponseEntity<InspeccionPcpCougar> actualizarInspeccion(
            @PathVariable Long id,
            @RequestBody InspeccionPcpCougar inspeccion) {
        InspeccionPcpCougar inspeccionActualizada = inspeccionService.actualizarInspeccion(id, inspeccion);
        return ResponseEntity.ok(inspeccionActualizada);
    }

    // Endpoint para obtener una inspección por ID
    @GetMapping("/{id}")
    public ResponseEntity<InspeccionPcpCougar> obtenerInspeccionPorId(@PathVariable Long id) {
        InspeccionPcpCougar inspeccion = inspeccionService.obtenerInspeccionPorId(id);
        return ResponseEntity.ok(inspeccion);
    }
    // Endpoint para obtener todas las inspecciones
    @GetMapping
    public ResponseEntity<List<InspeccionPcpCougar>> obtenerTodasInspecciones() {
        List<InspeccionPcpCougar> inspecciones = inspeccionService.obtenerTodasInspecciones();
        return ResponseEntity.ok(inspecciones);
    }
}
