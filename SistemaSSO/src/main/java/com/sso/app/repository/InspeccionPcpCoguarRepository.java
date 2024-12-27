package com.sso.app.repository;

import com.sso.app.entity.inspeccion.pcpcougarcd50.InspeccionPcpCougar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InspeccionPcpCoguarRepository extends JpaRepository<InspeccionPcpCougar, Long> {
}

