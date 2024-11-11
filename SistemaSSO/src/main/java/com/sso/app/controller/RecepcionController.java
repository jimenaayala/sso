package com.sso.app.controller;

import com.sso.app.entity.Recepcion;
import com.sso.app.service.RecepcionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/recepcion")
@AllArgsConstructor
@CrossOrigin

public class RecepcionController  {
    private final RecepcionService recepcionService;

    @PostMapping("/agregar")
    public ResponseEntity<Recepcion> agregarInspeccion(@RequestBody Recepcion recepcion) {
        Recepcion nuevoItem = recepcionService.agregarRecepcion(recepcion);
        return new ResponseEntity<>(nuevoItem, HttpStatus.CREATED); // Devuelve el ítem y un código 201 (CREATED)
    }

    // Endpoint para obtener todos los ítems de inspección con ResponseEntity
    @GetMapping("/todas")
    public ResponseEntity<List<Recepcion>> obtenerInspecciones() {
        List<Recepcion> lista = StreamSupport.stream(recepcionService.obtenerTodasLasRecepciones().spliterator(), false)
                .collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK); // Devuelve la lista y un código 200 (OK)
    }

}
