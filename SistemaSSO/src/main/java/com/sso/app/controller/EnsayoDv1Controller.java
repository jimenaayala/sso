package com.sso.app.controller;

import com.sso.app.entity.ensayo.EnsayoDv1;
import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.service.EnsayoDv1Service;
import com.sso.app.service.EnsayoMiniGService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ensayos/dv1")
@AllArgsConstructor
public class EnsayoDv1Controller {
    private final EnsayoDv1Service ensayoDv1Service;



    // Endpoint para crear una nuevo Ensayo
    @PostMapping
    public ResponseEntity<EnsayoDv1> crearEnsayo(@RequestBody EnsayoDv1 ensayo) {

        EnsayoDv1 nuevoEnsayo = this.ensayoDv1Service.guardarEnsayo(ensayo);
        return ResponseEntity.ok(nuevoEnsayo);
    }
    // Endpoint para actualizar un ensayo
    @PutMapping("/{id}")
    public ResponseEntity<EnsayoDv1> actualizarEnsayo(
            @PathVariable Long id,
            @RequestBody EnsayoDv1 ensayo) {
        EnsayoDv1 ensayoActualizado = this.ensayoDv1Service.actualizarEnsayo(id, ensayo);
        return ResponseEntity.ok(ensayoActualizado);
    }

    // Endpoint para obtener un ensayo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EnsayoDv1> obtenerEnsayoPorId(@PathVariable Long id) {
        EnsayoDv1 ensayo = ensayoDv1Service.obtenerEnsayoPorId(id);
        return ResponseEntity.ok(ensayo);
    }

    // Endpoint para obtener todas los ensayos
    @GetMapping
    public ResponseEntity<List<EnsayoDv1>> obtenerTodosEnsayos() {
        List<EnsayoDv1> ensayos = ensayoDv1Service.obtenerTodosEnsayos();
        return ResponseEntity.ok(ensayos);
    }
}
