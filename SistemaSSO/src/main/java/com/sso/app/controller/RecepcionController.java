package com.sso.app.controller;

import com.sso.app.entity.Imagen;
import com.sso.app.entity.Recepcion;
import com.sso.app.repository.ImagenRepository;
import com.sso.app.service.ImagenService;
import com.sso.app.service.RecepcionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/recepcion")
@AllArgsConstructor
@CrossOrigin

public class RecepcionController {

    private final RecepcionService recepcionService;
    private final ImagenService imagenService;
    private final ImagenRepository imagenRepository;

    //Buscar por id, sofDelete..

    @PostMapping
    public ResponseEntity<Recepcion> crearRecepcion(@RequestBody Recepcion recepcion) {
        // Asegúrate de que el objeto nuevo no tiene `id` antes de crear
        if (recepcion.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El `id` no debe estar presente para crear una nueva Recepcion");
        }
        Recepcion nuevaRecepcion = recepcionService.guardarOActualizarRecepcion(recepcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRecepcion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recepcion> actualizarRecepcion(@PathVariable Long id, @RequestBody Recepcion recepcion) {
        // Asegúrate de que el id coincide y que es una actualización
        if (!id.equals(recepcion.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El `id` en la URL debe coincidir con el `id` en el cuerpo de la solicitud");
        }
        Recepcion recepcionActualizada = recepcionService.guardarOActualizarRecepcion(recepcion);
        return ResponseEntity.ok(recepcionActualizada);
    }

    @PutMapping("/{id}/soft-delete")
    public ResponseEntity<Void> eliminarRecepcionDeFormaLogica(@PathVariable Long id) {
        recepcionService.deletedById(id); // Llamada al servicio para realizar el soft delete
        return ResponseEntity.noContent().build(); // Responder con un 204 No Content
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recepcion> buscarRecepcionPorId(@PathVariable Long id) {
        Recepcion recepcion = recepcionService.buscarPorId(id);
        return ResponseEntity.ok(recepcion);
    }
    /**
     * Endpoint para subir imágenes asociadas a una recepción
     *
     * @param recepcionId ID de la recepción a la que se asociará la imagen
     * @param file Archivo de imagen a subir
     * @param descripcion Descripción opcional de la imagen
     * @param publicar Si la imagen debe mostrarse en reportes (por defecto true)
     * @return Respuesta con datos de la imagen guardada
     */
    @PostMapping(value = "/{recepcionId}/subir-imagen")
    public ResponseEntity<?> subirImagen(
            @PathVariable Long recepcionId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "descripcion", required = false, defaultValue = "") String descripcion,
            @RequestParam(value = "publicar", required = false, defaultValue = "true") boolean publicar) {
        
        try {
            // Verificar si la recepción existe
            Recepcion recepcion = recepcionService.buscarPorId(recepcionId);
            if (recepcion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Recepción no encontrada con ID: " + recepcionId);
            }

            // Subir la imagen y obtener su URL
            String imageUrl = imagenService.subirImagen(file);

            // Crear y guardar la entidad Imagen con sus relaciones
            Imagen imagen = new Imagen();
            imagen.setUrl(imageUrl);
            imagen.setDescripcion(descripcion);
            imagen.setPublicar(publicar);
            imagen.setRecepcion(recepcion);
            Imagen imagenGuardada = imagenRepository.save(imagen);

            // Crear respuesta con detalles completos
            Map<String, Object> response = new HashMap<>();
            response.put("id", imagenGuardada.getId());
            response.put("url", imagenGuardada.getUrl());
            response.put("descripcion", imagenGuardada.getDescripcion() != null ? imagenGuardada.getDescripcion() : "");
            response.put("publicar", imagenGuardada.isPublicar());
            response.put("mensaje", "Imagen subida exitosamente");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log del error para depuración
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen: " + e.getMessage());
        }
    }
}
