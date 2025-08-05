package com.sso.app.controller;

import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import com.sso.app.service.InspeccionPcpMiniGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspecciones/minig")
public class InspeccionPcpMiniGController {

    @Autowired
    private InspeccionPcpMiniGService inspeccionService;

    // Endpoint para crear una nueva inspección
    @PostMapping
    public ResponseEntity<InspeccionPcpMiniG> crearInspeccion(@RequestBody InspeccionPcpMiniG inspeccion) {
        InspeccionPcpMiniG nuevaInspeccion = inspeccionService.crearInspeccion(inspeccion);
        return ResponseEntity.ok(nuevaInspeccion);
    }

    // Endpoint para actualizar una inspección existente
    @PutMapping("/{id}")
    public ResponseEntity<InspeccionPcpMiniG> actualizarInspeccion(
            @PathVariable Long id,
            @RequestBody InspeccionPcpMiniG inspeccion) {
        InspeccionPcpMiniG inspeccionActualizada = inspeccionService.actualizarInspeccion(id, inspeccion);
        return ResponseEntity.ok(inspeccionActualizada);
    }

    // Endpoint para obtener una inspección por ID
    @GetMapping("/{id}")
    public ResponseEntity<InspeccionPcpMiniG> obtenerInspeccionPorId(@PathVariable Long id) {
        InspeccionPcpMiniG inspeccion = inspeccionService.obtenerInspeccionPorId(id);
        return ResponseEntity.ok(inspeccion);
    }

    // Endpoint para obtener todas las inspecciones
    @GetMapping
    public ResponseEntity<List<InspeccionPcpMiniG>> obtenerTodasInspecciones() {
        List<InspeccionPcpMiniG> inspecciones = inspeccionService.obtenerTodasInspecciones();
        return ResponseEntity.ok(inspecciones);
    }
}
