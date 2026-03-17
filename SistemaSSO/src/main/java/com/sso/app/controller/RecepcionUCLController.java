package com.sso.app.controller;

import com.sso.app.entity.UCLRecepcion.RecepcionUCL;
import com.sso.app.service.RecepcionUCLService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<RecepcionUCL> crearRecepcionUCL(@RequestBody RecepcionUCL recepcionUCL) {
        if (recepcionUCL.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id no debe estar presente para crear");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recepcionUCLService.guardarOActualizarRecepcionUCL(recepcionUCL));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecepcionUCL> actualizarRecepcionUCL(@PathVariable Long id, @RequestBody RecepcionUCL recepcionUCL) {
        recepcionUCL.setId(id);
        return ResponseEntity.ok(recepcionUCLService.guardarOActualizarRecepcionUCL(recepcionUCL));
    }

    @PutMapping("/{id}/soft-delete")
    public ResponseEntity<Void> eliminarRecepcionUCLDeFormaLogica(@PathVariable Long id) {
        recepcionUCLService.deletedById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecepcionUCL> buscarRecepcionUCLPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recepcionUCLService.buscarPorId(id));
    }
}

