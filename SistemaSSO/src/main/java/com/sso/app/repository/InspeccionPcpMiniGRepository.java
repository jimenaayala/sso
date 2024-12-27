package com.sso.app.repository;
import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspeccionPcpMiniGRepository extends JpaRepository<InspeccionPcpMiniG, Long>{
}
