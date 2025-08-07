package com.sso.app.service;

import com.sso.app.controller.dto.ImagenDTO;
import com.sso.app.entity.Imagen;
import com.sso.app.entity.Recepcion;
import com.sso.app.entity.ensayo.EnsayoCougar;
import com.sso.app.entity.ensayo.EnsayoDv1;
import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.entity.ensayo.EnsayoVh60;
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

    private final EnsayoCougarRepository ensayoCougarRepository;
    private final EnsayoDv1Repository ensayoDv1Repository;
    private final EnsayoMiniGRepository ensayoMiniGRepository;
    private final EnsayoVH60Repository ensayoVh60Repository;

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    public ImagenService(ImagenRepository imagenRepository,
                         RecepcionRepository recepcionRepository,
                         InspeccionPcpVh60Repository inspeccionPcpVh60Repository,
                         InspeccionPcpMiniGRepository inspeccionPcpMiniGRepository,
                         InspeccionPcpCoguarRepository inspeccionPcpCoguarRepository,
                         InspeccionPcpDv1Repository inspeccionPcpDv1Repository,
                         EnsayoCougarRepository ensayoCougarRepository,
                         EnsayoDv1Repository ensayoDv1Repository,
                         EnsayoMiniGRepository ensayoMiniGRepository,
                         EnsayoVH60Repository ensayoVh60Repository) {
        this.imagenRepository = imagenRepository;
        this.recepcionRepository = recepcionRepository;
        this.inspeccionPcpVh60Repository = inspeccionPcpVh60Repository;
        this.inspeccionPcpMiniGRepository = inspeccionPcpMiniGRepository;
        this.inspeccionPcpCoguarRepository = inspeccionPcpCoguarRepository;
        this.inspeccionPcpDv1Repository = inspeccionPcpDv1Repository;
        this.ensayoCougarRepository = ensayoCougarRepository;
        this.ensayoDv1Repository = ensayoDv1Repository;
        this.ensayoMiniGRepository = ensayoMiniGRepository;
        this.ensayoVh60Repository = ensayoVh60Repository;
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
    public Imagen modificarImagenRecepcion(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

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

    public Imagen modificarImagenPcpVh60(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

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

    public Imagen modificarImagenPcpMiniG(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

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
    public Imagen modificarImagenPcpCougar(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

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

    public Imagen modificarImagenPcpDv1(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorInspeccionDv1(Long inspeccionDv1Id) {
        return imagenRepository.findByInspeccionPcpDv1Id(inspeccionDv1Id).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }

    public Imagen guardarImagenEnsayoCougar(MultipartFile file, String descripcion, boolean publicar, Long ensayoCougarId) throws IOException {
        EnsayoCougar ensayo = ensayoCougarRepository.findById(ensayoCougarId)
                .orElseThrow(() -> new RuntimeException("Ensayo Cougar no encontrado"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setEnsayoCougar(ensayo);

        return imagenRepository.save(imagen);
    }

    public Imagen modificarImagenEnsayoCougar(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorEnsayoCougar(Long ensayoCougarId) {
        return imagenRepository.findByEnsayoCougarId(ensayoCougarId).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }

    public Imagen guardarImagenEnsayoDv1(MultipartFile file, String descripcion, boolean publicar, Long ensayoDv1Id) throws IOException {
        EnsayoDv1 ensayo = ensayoDv1Repository.findById(ensayoDv1Id)
                .orElseThrow(() -> new RuntimeException("Ensayo Dv1 no encontrado"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setEnsayoDv1(ensayo);

        return imagenRepository.save(imagen);
    }

    public Imagen modificarImagenEnsayoDv1(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorEnsayoDv1(Long ensayoDv1Id) {
        return imagenRepository.findByEnsayoDv1Id(ensayoDv1Id).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }

    public Imagen guardarImagenEnsayoMiniG(MultipartFile file, String descripcion, boolean publicar, Long ensayoMiniGId) throws IOException {
        EnsayoMiniG ensayo = ensayoMiniGRepository.findById(ensayoMiniGId)
                .orElseThrow(() -> new RuntimeException("Ensayo MiniG no encontrado"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setEnsayoMiniG(ensayo);

        return imagenRepository.save(imagen);
    }

    public Imagen modificarImagenEnsayoMiniG(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorEnsayoMiniG(Long ensayoMiniGId) {
        return imagenRepository.findByEnsayoMiniGId(ensayoMiniGId).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }

    public Imagen guardarImagenEnsayoVh60(MultipartFile file, String descripcion, boolean publicar, Long ensayoVh60Id) throws IOException {
        EnsayoVh60 ensayo = ensayoVh60Repository.findById(ensayoVh60Id)
                .orElseThrow(() -> new RuntimeException("Ensayo Vh60 no encontrado"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        Imagen imagen = new Imagen();
        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);
        imagen.setEnsayoVh60(ensayo);

        return imagenRepository.save(imagen);
    }

    public Imagen modificarImagenEnsayoVh60(Long imagenId, MultipartFile file, String descripcion, boolean publicar) throws IOException {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String ruta = new File(".").getCanonicalPath() + File.separator + uploadDir + File.separator + nombreArchivo;

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        imagen.setUrl("/uploads/" + nombreArchivo);
        imagen.setDescripcion(descripcion);
        imagen.setPublicar(publicar);

        return imagenRepository.save(imagen);
    }

    public List<ImagenDTO> obtenerImagenesPorEnsayoVh60(Long ensayoVh60Id) {
        return imagenRepository.findByEnsayoVh60Id(ensayoVh60Id).stream()
                .map(img -> new ImagenDTO(img.getUrl(), img.getDescripcion(), img.isPublicar()))
                .toList();
    }

}
