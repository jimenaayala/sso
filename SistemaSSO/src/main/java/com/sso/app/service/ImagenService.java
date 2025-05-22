package com.sso.app.service;

import com.sso.app.controller.dto.ImagenDTO;
import com.sso.app.entity.Imagen;
import com.sso.app.repository.ImagenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import jakarta.annotation.PostConstruct;

@Service

public class ImagenService {
    private final ImagenRepository imagenRepository;

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;
    
    private Path fileStoragePath;

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }
    
    @PostConstruct
    public void init() {
        try {
            fileStoragePath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio donde se guardarán los archivos subidos", e);
        }
    }

    public String subirImagen(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío");
        }

        // Normalizar el nombre del archivo para evitar problemas
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        // Validar que es una imagen
        if (!esImagen(originalFilename)) {
            throw new IOException("Solo se permiten archivos de imagen");
        }
        
        // Crear un nombre único para evitar colisiones
        String filename = UUID.randomUUID().toString() + "_" + originalFilename;
        
        // Construir la ruta completa donde se guardará el archivo
        Path targetLocation = fileStoragePath.resolve(filename);
        
        // Guardar el archivo en el servidor
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        
        // Construir y retornar la URL para acceder al archivo
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(filename)
                .toUriString();
                
        return fileDownloadUri;
    }
    
    private boolean esImagen(String filename) {
        // Verificar extensiones comunes de imágenes
        String[] extensionesImagen = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        String lowercaseFilename = filename.toLowerCase();
        
        for (String extension : extensionesImagen) {
            if (lowercaseFilename.endsWith(extension)) {
                return true;
            }
        }
        
        return false;
    }

    public void eliminarImagen(String imageUrl) throws IOException {
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IOException("URL de imagen inválida");
        }
        
        // Obtener solo el nombre del archivo de la URL
        String filename = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        
        // Construir la ruta completa del archivo a eliminar
        Path filePath = fileStoragePath.resolve(filename);
        
        // Eliminar el archivo si existe
        Files.deleteIfExists(filePath);
    }
    public List<ImagenDTO> obtenerImagenesPorRecepcion(Long recepcionId) {
        return imagenRepository.findByRecepcionId(recepcionId).stream()
                .map(img -> new ImagenDTO(
                        img.getId(),
                        img.getUrl(),
                        img.getDescripcion(),
                        img.isPublicar()
                ))
                .toList();
    }
}
