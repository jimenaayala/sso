package com.sso.app.controller;

import com.sso.app.entity.InspeccionUCL.InspeccionUCL;
import com.sso.app.service.InspeccionUCLService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inspeccionesUCL")
@AllArgsConstructor
public class InspeccionUCLController {

    private final InspeccionUCLService inspeccionUCLService;

    @PostMapping
    public ResponseEntity<InspeccionUCL> crearInspeccionUCL (@RequestBody InspeccionUCL inspeccionUCL){
        InspeccionUCL nuevaInspeccion = inspeccionUCLService.crearInspeccionUCL(inspeccionUCL);
        return ResponseEntity.ok(nuevaInspeccion);
    }

    @PutMapping
    public ResponseEntity<InspeccionUCL> actualizarInspeccionUCL (
            @PathVariable Long id,
            @RequestBody InspeccionUCL inspeccionUCL) {
        InspeccionUCL inspeccionUCLActualizada = inspeccionUCLService.actualizarInspeccionUCL(id,inspeccionUCL);
return ResponseEntity.ok(inspeccionUCLActualizada);
}

    // Endpoint para obtener una inspección por ID
    @GetMapping("/{id}")
    public ResponseEntity<InspeccionUCL> obtenerInspeccionUCLPorId(@PathVariable Long id) {
        InspeccionUCL inspeccion = inspeccionUCLService.obtenerInspeccionUCLPorId(id);
        return ResponseEntity.ok(inspeccion);
    }
    // Endpoint para obtener todas las inspecciones
    @GetMapping
    public ResponseEntity<List<InspeccionUCL>> obtenerTodasInspecciones() {
        List<InspeccionUCL> inspecciones = inspeccionUCLService.obtenerTodasInspeccionesUCL();
        return ResponseEntity.ok(inspecciones);
    }

}
