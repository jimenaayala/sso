package com.sso.app.controller;

import com.sso.app.entity.Recepcion;
import com.sso.app.service.RecepcionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/recepcion")
@AllArgsConstructor
@CrossOrigin

public class RecepcionController {

    private final RecepcionService recepcionService;

//    @Override
//    protected List<Recepcion> findAllActive(){
//        return recepcionService.findAllActive();
//    }
//
//    @Override
//    protected Optional<Recepcion> findById(Long id) {
//        return recepcionService.findById(id);
//    }
//
//    @Override
//    protected Recepcion save(Recepcion entity) {
//        return recepcionService.save(entity);
//    }
//
//    @Override
//    protected void deleteById(Long id) {
//        recepcionService.deleteById(id);
//    }
//
//    @Override
//    protected void setId(Recepcion entity, Long id) {
//        entity.setId(id);
//    }

    @PostMapping
    public ResponseEntity<Recepcion> crearRecepcion(@RequestBody Recepcion recepcion) {
        // Llamamos al servicio para crear la recepción con todos los ítems
        Recepcion nuevaRecepcion = recepcionService.crearRecepcion(recepcion);
        return ResponseEntity.ok(nuevaRecepcion);
    }

}
