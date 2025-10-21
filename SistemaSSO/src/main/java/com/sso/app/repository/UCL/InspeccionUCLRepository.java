package com.sso.app.repository.UCL;

import com.sso.app.entity.InspeccionUCL.InspeccionUCL;
import com.sso.app.entity.UCLRecepcion.RecepcionUCL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspeccionUCLRepository extends JpaRepository<InspeccionUCL, Long> {
}
