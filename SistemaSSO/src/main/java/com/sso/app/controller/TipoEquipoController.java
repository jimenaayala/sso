package com.sso.app.controller;

import com.sso.app.entity.TipoEquipo;
import com.sso.app.service.TipoEquipoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipos")
@AllArgsConstructor
@CrossOrigin
public class TipoEquipoController {
    private final TipoEquipoService equipoService;

    @PostMapping
    public ResponseEntity<TipoEquipo> createEquipo(@RequestBody TipoEquipo equipo) {
        TipoEquipo savedEquipo = equipoService.addEquipos(equipo);
        return ResponseEntity.ok(savedEquipo);
    }

    @GetMapping
    public ResponseEntity<List<TipoEquipo>> getAllEquipos() {
        List<TipoEquipo> equipos = equipoService.findAll();
        return ResponseEntity.ok(equipos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TipoEquipo>> getEquiposByModelo(@RequestParam String modelo) {
        List<TipoEquipo> equipos = equipoService.findByModelo(modelo);
        return ResponseEntity.ok(equipos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        equipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
