package com.sso.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImagenService {

    private final String UPLOAD_DIR = "uploads/"; // Ruta donde se guardarán las imágenes en el servidor

    public String subirImagen(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío");
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filepath = Paths.get(UPLOAD_DIR, filename);

        // Crear el directorio si no existe
        Files.createDirectories(filepath.getParent());

        // Guardar la imagen en el servidor
        Files.write(filepath, file.getBytes());

        // Retornar la URL de la imagen (ajustar si usamos almacenamiento externo)
        return "/uploads/" + filename;
    }

    public void eliminarImagen(String imageUrl) throws IOException {
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IOException("URL de imagen inválida");
        }

        Path filePath = Paths.get(UPLOAD_DIR, imageUrl.replace("/uploads/", ""));
        Files.deleteIfExists(filePath);
    }
}
