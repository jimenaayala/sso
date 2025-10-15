package com.sso.app.controller;

import com.sso.app.entity.UCL.RecepcionUCL;
import com.sso.app.service.RecepcionUCLService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/recepcionesucl")
@AllArgsConstructor
@CrossOrigin
public class RecepcionUCLController {

    private final RecepcionUCLService recepcionUCLService;

    // Crear nueva Recepción UCL
    @PostMapping
    public ResponseEntity<RecepcionUCL> crearRecepcionUCL(@RequestBody RecepcionUCL recepcionUCL) {
        // Validación: no debe tener ID al crear
        if (recepcionUCL.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El `id` no debe estar presente para crear una nueva Recepción UCL");
        }
        RecepcionUCL nueva = recepcionUCLService.guardarOActualizarRecepcionUCL(recepcionUCL);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar una Recepción UCL existente
    @PutMapping("/{id}")
    public ResponseEntity<RecepcionUCL> actualizarRecepcionUCL(@PathVariable Long id, @RequestBody RecepcionUCL recepcionUCL) {
        if (!id.equals(recepcionUCL.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El `id` en la URL debe coincidir con el `id` en el cuerpo de la solicitud");
        }
        RecepcionUCL actualizada = recepcionUCLService.guardarOActualizarRecepcionUCL(recepcionUCL);
        return ResponseEntity.ok(actualizada);
    }

    // Soft delete
    @PutMapping("/{id}/soft-delete")
    public ResponseEntity<Void> eliminarRecepcionUCLDeFormaLogica(@PathVariable Long id) {
        recepcionUCLService.deletedById(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecepcionUCL> buscarRecepcionUCLPorId(@PathVariable Long id) {
        RecepcionUCL recepcion = recepcionUCLService.buscarPorId(id);
        return ResponseEntity.ok(recepcion);
    }
}