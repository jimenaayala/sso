package com.sso.app.repository;

import com.sso.app.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    List<Imagen> findByRecepcionId(Long recepcionId);
    List<Imagen> findByInspeccionPcpVh60Id(Long inspeccionPcpVh60Id);

}
