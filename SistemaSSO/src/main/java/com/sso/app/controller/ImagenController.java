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

    // --- RECEPCIÓN ---

    @PostMapping(value = "/recepcion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

    @PutMapping(value = "/recepcion/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> modificarImagenRecepcion(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar) {
        try {
            Imagen imagen = imagenService.modificarImagenRecepcion(id, file, descripcion, publicar);
            return ResponseEntity.ok("Imagen modificada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // --- VH60 ---
    @PostMapping(value = "/inspeccion/vh60", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenInspeccionVh60(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("inspeccionPcpVh60Id") Long id) {
        try {
            Imagen imagen = imagenService.guardarImagenPcpVh60(file, descripcion, publicar, id);
            return ResponseEntity.ok("imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/inspeccion/vh60/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesInspeccionVh60(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorInspeccionVh60(id));
    }

    @PutMapping(value = "/inspeccion/vh60/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> modificarImagenVh60(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar) {
        try {
            Imagen imagen = imagenService.modificarImagenPcpVh60(id, file, descripcion, publicar);
            return ResponseEntity.ok("Imagen modificada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // --- MINI-G ---
    @PostMapping(value = "/inspeccion/minig", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenInspeccionMiniG(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("inspeccionPcpMiniGId") Long id) {
        try {
            Imagen imagen = imagenService.guardarImagenPcpMiniG(file, descripcion, publicar, id);
            return ResponseEntity.ok("imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/inspeccion/minig/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesInspeccionMiniG(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorInspeccionMiniG(id));
    }

    @PutMapping(value = "/inspeccion/minig/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> modificarImagenMiniG(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar) {
        try {
            Imagen imagen = imagenService.modificarImagenPcpMiniG(id, file, descripcion, publicar);
            return ResponseEntity.ok("Imagen modificada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // --- DV-1 ---
    @PostMapping(value = "/inspeccion/dv1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenInspeccionDv1(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("inspeccionPcpDv1Id") Long id) {
        try {
            Imagen imagen = imagenService.guardarImagenPcpDv1(file, descripcion, publicar, id);
            return ResponseEntity.ok("imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/inspeccion/dv1/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesInspeccionDv1(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorInspeccionDv1(id));
    }

    @PutMapping(value = "/inspeccion/dv1/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> modificarImagenDv1(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar) {
        try {
            Imagen imagen = imagenService.modificarImagenPcpDv1(id, file, descripcion, publicar);
            return ResponseEntity.ok("Imagen modificada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // --- COUGAR ---
    @PostMapping(value = "/inspeccion/cougar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> subirImagenInspeccionCougar(
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar,
            @RequestParam("inspeccionPcpCougarId") Long id) {
        try {
            Imagen imagen = imagenService.guardarImagenPcpCougar(file, descripcion, publicar, id);
            return ResponseEntity.ok("imagen guardada: " + imagen.getUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/inspeccion/cougar/{id}")
    public ResponseEntity<List<ImagenDTO>> listarImagenesInspeccionCougar(@PathVariable Long id) {
        return ResponseEntity.ok(imagenService.obtenerImagenesPorInspeccionCougar(id));
    }

    @PutMapping(value = "/inspeccion/cougar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> modificarImagenCougar(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("publicar") boolean publicar) {
        try {
            Imagen imagen = imagenService.modificarImagenPcpCougar(id, file, descripcion, publicar);
            return ResponseEntity.ok("Imagen modificada: " + imagen.getUrl());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}