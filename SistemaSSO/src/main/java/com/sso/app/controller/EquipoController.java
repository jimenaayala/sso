package com.sso.app.controller;

import com.sso.app.entity.Cliente;
import com.sso.app.entity.Equipo;
import com.sso.app.service.EquipoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/equipos")
@AllArgsConstructor
@CrossOrigin
public class EquipoController {
    private final EquipoService equipoService;

    @PostMapping
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        Equipo savedEquipo = equipoService.addEquipos(equipo);
        return ResponseEntity.ok(savedEquipo);
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipos = equipoService.findAll();
        return ResponseEntity.ok(equipos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> getEquiposByModelo(@RequestParam String modelo) {
        List<Equipo> equipos = equipoService.findByModelo(modelo);
        return ResponseEntity.ok(equipos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        equipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
