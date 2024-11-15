package com.sso.app.controller;

import com.sso.app.entity.Recepcion;
import com.sso.app.service.RecepcionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/recepcion")
@AllArgsConstructor
@CrossOrigin

public class RecepcionController {

    private final RecepcionService recepcionService;


    @PostMapping
    public ResponseEntity<Recepcion> crearRecepcion(@RequestBody Recepcion recepcion) {
        // Asegúrate de que el objeto nuevo no tiene `id` antes de crear
        if (recepcion.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El `id` no debe estar presente para crear una nueva Recepcion");
        }
        Recepcion nuevaRecepcion = recepcionService.guardarOActualizarRecepcion(recepcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRecepcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recepcion> actualizarRecepcion(@PathVariable Long id, @RequestBody Recepcion recepcion) {
        // Asegúrate de que el id coincide y que es una actualización
        if (!id.equals(recepcion.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El `id` en la URL debe coincidir con el `id` en el cuerpo de la solicitud");
        }
        Recepcion recepcionActualizada = recepcionService.guardarOActualizarRecepcion(recepcion);
        return ResponseEntity.ok(recepcionActualizada);
    }


}
