package com.sso.app.controller;

import com.sso.app.controller.dto.ImagenDTO;
import com.sso.app.entity.Imagen;
import com.sso.app.service.ImagenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {

    private final ImagenService imagenService;

    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @PostMapping("/subir")
    public ResponseEntity<String> subirImagen(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("recepcionId") Long recepcionId) {
        try {
            Imagen imagen = imagenService.guardarImagen(file, descripcion, publicar, recepcionId);
            return ResponseEntity.ok("Imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/recepcion/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenes(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorRecepcion(id));
    }
}
