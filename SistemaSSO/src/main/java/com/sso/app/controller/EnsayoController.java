package com.sso.app.controller;

import com.sso.app.entity.Ensayo;
import com.sso.app.entity.Orden;
import com.sso.app.service.EnsayoService;
import com.sso.app.service.OrdenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;  // Importación necesaria
import java.util.Optional;  // Importación necesaria

@RestController
@RequestMapping("api/ensayo")
@AllArgsConstructor
@CrossOrigin

public class EnsayoController extends BaseController<Ensayo> {
    private final EnsayoService ensayoService;
    private final OrdenService ordenService;

    @Override
    protected List<Ensayo> findAllActive(){
        return ensayoService.findAllActive();
    }
    @Override
    protected Optional<Ensayo> findById(Long id) {
        return ensayoService.findById(id);
    }

    @Override
    protected Ensayo save(Ensayo entity) {
        return ensayoService.save(entity);
    }

    @Override
    protected void deleteById(Long id) {
        ensayoService.deleteById(id);
    }

    @Override
    protected void setId(Ensayo entity, Long id) {
        entity.setId(id);
    }

/*    @PostMapping("/orden/{ordenId}")
    public ResponseEntity<Ensayo> create(@RequestBody Ensayo ensayo, @PathVariable Long ordenId) {
        // Buscar la orden por su ID
        Optional<Orden> ordenOptional = ordenService.findById(ordenId);

        if (ordenOptional.isEmpty()) {
            // Manejar caso donde la orden no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Obtener la orden existente
        Orden orden = ordenOptional.get();

        // Verificar si ya tiene un ensayo asignado
        if (orden.getEnsayo() != null) {
            // Enviar un mensaje de que el ensayo ya está asignado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Asignar el ensayo a la orden
        orden.setEnsayo(ensayo);

        // Guardar el ensayo y la orden actualizada
        ensayoService.save(ensayo);
        ordenService.save(orden);

        return ResponseEntity.ok(ensayo);
    }*/

    @PostMapping("/orden/{ordenId}")
    public ResponseEntity<?> create(@RequestBody Ensayo ensayo, @PathVariable Long ordenId) {
        // Buscar la orden por su ID o lanzar una excepción si no se encuentra
        Orden orden = ordenService.findById(ordenId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada"));

        // Verificar si ya tiene un ensayo asignado
        if (orden.getEnsayo() != null) {
            // Respuesta de conflicto si la orden ya tiene un ensayo asignado
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La orden ya tiene un ensayo asignado.");
        }

        // Asignar el ensayo a la orden
        orden.setEnsayo(ensayo);

        // Guardar el ensayo y la orden en una transacción
        ensayoService.save(ensayo);  // Guardar el ensayo
        ordenService.save(orden);    // Guardar la orden con el ensayo asignado

        // Retornar la respuesta con el ensayo creado
        return ResponseEntity.status(HttpStatus.CREATED).body(ensayo);
    }

}
