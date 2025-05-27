package com.sso.app.service;

import com.sso.app.controller.dto.ImagenDTO;
import com.sso.app.entity.Imagen;
import com.sso.app.entity.Recepcion;
import com.sso.app.repository.ImagenRepository;
import com.sso.app.repository.RecepcionRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;
    private final RecepcionRepository recepcionRepository;

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    public ImagenService(ImagenRepository imagenRepository, RecepcionRepository recepcionRepository) {
        this.imagenRepository = imagenRepository;
        this.recepcionRepository = recepcionRepository;
    }

    public Imagen guardarImagen(MultipartFile file, String descripcion, boolean publicar, Long recepcionId) throws IOException {
        Recepcion recepcion = recepcionRepository.findById(recepcionId)
                .orElseThrow(() -> new RuntimeException("Recepcion no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setRecepcion(recepcion);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorRecepcion(Long recepcionId) {
        return imagenRepository.findByRecepcionId(recepcionId).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }
}
