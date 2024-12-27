package com.sso.app.repository;

import com.sso.app.entity.inspeccion.pcpdv1.InspeccionPcpDV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InspeccionPcpDv1Repository extends JpaRepository<InspeccionPcpDV1, Long> {
}
