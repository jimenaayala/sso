package com.sso.app.controller;

import com.sso.app.entity.Imagen;
import com.sso.app.repository.ImagenRepository;
import com.sso.app.service.ImagenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test/imagen")
@AllArgsConstructor
@CrossOrigin
public class ImagenTestController {

    private final ImagenService imagenService;
    private final ImagenRepository imagenRepository;

    /**
     * Endpoint simplificado para probar la subida de imágenes
     * No requiere ID de recepción ni otros parámetros complejos
     */
    @PostMapping("/upload")
    public ResponseEntity<?> testSubirImagen(@RequestParam("file") MultipartFile file) {
        try {
            // Subir la imagen y obtener su URL
            String imageUrl = imagenService.subirImagen(file);

            // Crear entidad imagen (sin asociarla a ninguna recepción)
            Imagen imagen = new Imagen();
            imagen.setUrl(imageUrl);
            imagen.setDescripcion("Imagen de prueba");
            imagen.setPublicar(true);
            
            Imagen imagenGuardada = imagenRepository.save(imagen);

            // Crear respuesta
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("id", imagenGuardada.getId());
            response.put("url", imagenGuardada.getUrl());
            response.put("mensaje", "Imagen subida exitosamente para pruebas");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error al subir la imagen: " + e.getMessage());
        }
    }
}
