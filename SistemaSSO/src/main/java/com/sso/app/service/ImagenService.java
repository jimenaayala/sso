package com.sso.app.service;

import com.sso.app.controller.dto.ImagenDTO;
import com.sso.app.entity.Imagen;
import com.sso.app.entity.Recepcion;
import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDv1;
import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import com.sso.app.entity.inspeccion.pcpvh60.InspeccionPcpVh60;
import com.sso.app.repository.*;

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
    private final InspeccionPcpVh60Repository inspeccionPcpVh60Repository;
    private final InspeccionPcpMiniGRepository inspeccionPcpMiniGRepository;
    private final InspeccionPcpCoguarRepository inspeccionPcpCoguarRepository;
    private final InspeccionPcpDv1Repository inspeccionPcpDv1Repository;

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    public ImagenService(ImagenRepository imagenRepository,
                         RecepcionRepository recepcionRepository,
                         InspeccionPcpVh60Repository inspeccionPcpVh60Repository,
                         InspeccionPcpMiniGRepository inspeccionPcpMiniGRepository,
                         InspeccionPcpCoguarRepository inspeccionPcpCoguarRepository,
                         InspeccionPcpDv1Repository inspeccionPcpDv1Repository) {
        this.imagenRepository = imagenRepository;
        this.recepcionRepository = recepcionRepository;
        this.inspeccionPcpVh60Repository = inspeccionPcpVh60Repository;
        this.inspeccionPcpMiniGRepository = inspeccionPcpMiniGRepository;
        this.inspeccionPcpCoguarRepository = inspeccionPcpCoguarRepository;
        this.inspeccionPcpDv1Repository = inspeccionPcpDv1Repository;
    }

    public Imagen guardarImagenRecepcion(MultipartFile file, String descripcion, boolean publicar, Long recepcionId) throws IOException {
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

    public Imagen guardarImagenPcpVh60(MultipartFile file, String descripcion, boolean publicar, Long inspeccionPcpVh60Id) throws IOException {
        InspeccionPcpVh60 inspeccionPcpVh60 = inspeccionPcpVh60Repository.findById(inspeccionPcpVh60Id)
                .orElseThrow(() -> new RuntimeException("InspeccionVh60 no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setInspeccionPcpVh60(inspeccionPcpVh60);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorInspeccionVh60(Long inspeccionVh60Id) {
        return imagenRepository.findByInspeccionPcpVh60Id(inspeccionVh60Id).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }

    public Imagen guardarImagenPcpMiniG(MultipartFile file, String descripcion, boolean publicar, Long inspeccionPcpMiniGId) throws IOException {
        InspeccionPcpMiniG inspeccionPcpMiniG = inspeccionPcpMiniGRepository.findById(inspeccionPcpMiniGId)
                .orElseThrow(() -> new RuntimeException("Inspeccion MiniG no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setInspeccionPcpMiniG(inspeccionPcpMiniG);

        return imagenRepository.save(imagen);
    }
    public List<ImagenDTO> obtenerImagenesPorInspeccionMiniG(Long inspeccionMiniGId) {
        return imagenRepository.findByInspeccionPcpMiniGId(inspeccionMiniGId).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }

    public Imagen guardarImagenPcpCougar(MultipartFile file, String descripcion, boolean publicar, Long inspeccionPcpCougarId) throws IOException {
        InspeccionPcpCougar inspeccionPcpCougar = inspeccionPcpCoguarRepository.findById(inspeccionPcpCougarId)
                .orElseThrow(() -> new RuntimeException("Inspeccion Cougar no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setInspeccionPcpCougar(inspeccionPcpCougar);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorInspeccionCougar(Long inspeccionCougarId) {
        return imagenRepository.findByInspeccionPcpCougarId(inspeccionCougarId).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }

    public Imagen guardarImagenPcpDv1(MultipartFile file, String descripcion, boolean publicar, Long inspeccionPcpDv1Id) throws IOException {
        InspeccionPcpDv1 inspeccionPcpDv1 = inspeccionPcpDv1Repository.findById(inspeccionPcpDv1Id)
                .orElseThrow(() -> new RuntimeException("Inspeccion Dv1 no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setInspeccionPcpDv1(inspeccionPcpDv1);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorInspeccionDv1(Long inspeccionDv1Id) {
        return imagenRepository.findByInspeccionPcpDv1Id(inspeccionDv1Id).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }
}
