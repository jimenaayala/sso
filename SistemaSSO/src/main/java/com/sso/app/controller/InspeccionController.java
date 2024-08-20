package com.sso.app.controller;

import com.sso.app.entity.Inspeccion;
import com.sso.app.service.InspeccionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/inspeccion")
@AllArgsConstructor // en el controller es necesario?
@CrossOrigin
public class InspeccionController {

    private final InspeccionService inspeccionService;
    //Alta
    @PostMapping
    public ResponseEntity<Inspeccion> createInspeccion(@RequestBody Inspeccion inspeccion){
        Inspeccion savedInspeccion = inspeccionService.save(inspeccion);
        return ResponseEntity.ok(savedInspeccion);
    }
    //Baja
    @PutMapping ("/softDelete/{id}")
    public ResponseEntity<Void> softDeleteInspeccion(@PathVariable Long id){
        inspeccionService.deletedById(id);
        return ResponseEntity.noContent().build();  //Devuelve 204 No Content si esta OK
    }
    //Modificacion
    @PutMapping("/{id}")
    public ResponseEntity<Inspeccion> updateInspeccion(@PathVariable Long id, @RequestBody Inspeccion inspeccion){
        inspeccion.setId(id);
        Inspeccion updateInspeccion = inspeccionService.save(inspeccion);
        return ResponseEntity.ok(updateInspeccion);
    }

    //Consulta todas con eliminado=false
    @GetMapping
    public ResponseEntity<List<Inspeccion>> getAllInspecciones(){
        List<Inspeccion> inspecciones = inspeccionService.findAllActive();
        return ResponseEntity.ok(inspecciones);
    }

    //Consulta especifica
    @GetMapping ("/buscar/{id}")
    public ResponseEntity<Inspeccion> findById (@PathVariable Long id){
        Optional<Inspeccion> inspeccion = inspeccionService.findById(id);
        return inspeccion.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
}
