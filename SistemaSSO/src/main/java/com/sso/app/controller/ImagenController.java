package com.sso.app.controller;

import com.sso.app.controller.dto.ImagenDTO;
import com.sso.app.entity.Imagen;
import com.sso.app.service.ImagenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/subirRecepcion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenRecepcion(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("recepcionId") Long recepcionId) {
        try {
            Imagen imagen = imagenService.guardarImagenRecepcion(file, descripcion, publicar, recepcionId);
            return ResponseEntity.ok("Imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/recepcion/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesRecepcion(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorRecepcion(id));
    }

    @PostMapping(value = "/subirInspeccionVh60", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenInspeccionVh60(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("inspeccionPcpVh60Id") Long inspeccionPcpVh60Id) {
        try {
            Imagen imagen = imagenService.guardarImagenPcpVh60(file, descripcion, publicar, inspeccionPcpVh60Id);
            return ResponseEntity.ok("Imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/inspeccionPcpVh60/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesInspeccionVh60(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorInspeccionVh60(id));
    }

    @PostMapping(value = "/subirInspeccionMiniG", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenInspeccionMiniG(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("inspeccionPcpMiniGId") Long inspeccionPcpMiniGId) {
        try {
            Imagen imagen = imagenService.guardarImagenPcpMiniG(file, descripcion, publicar, inspeccionPcpMiniGId);
            return ResponseEntity.ok("Imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/inspeccionPcpMiniG/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesInspeccionMiniG(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorInspeccionMiniG(id));
    }

    @PostMapping(value = "/subirInspeccionCougar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenInspeccionCougar(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("inspeccionPcpCougarId") Long inspeccionPcpCougarId) {
        try {
            Imagen imagen = imagenService.guardarImagenPcpCougar(file, descripcion, publicar, inspeccionPcpCougarId);
            return ResponseEntity.ok("Imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/inspeccionPcpCougar/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesInspeccionCougar(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorInspeccionCougar(id));
    }

    @PostMapping(value = "/subirInspeccionDv1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenInspeccionDv1(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("inspeccionPcpDv1Id") Long inspeccionPcpDv1Id) {
        try {
            Imagen imagen = imagenService.guardarImagenPcpDv1(file, descripcion, publicar, inspeccionPcpDv1Id);
            return ResponseEntity.ok("Imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/inspeccionPcpDv1/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesInspeccionDv1(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorInspeccionDv1(id));
    }
}

