package com.sso.app.repository;

import com.sso.app.entity.ensayo.EnsayoMiniG;
import com.sso.app.entity.inspeccion.pcpminig.InspeccionPcpMiniG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsayoMiniGRepository extends CrudRepository<EnsayoMiniG, Long> {
}
