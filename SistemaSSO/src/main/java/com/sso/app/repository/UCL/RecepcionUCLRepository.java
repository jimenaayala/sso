package com.sso.app.repository.UCL;

import com.sso.app.entity.Recepcion;
import com.sso.app.entity.UCL.RecepcionUCL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecepcionUCLRepository  extends JpaRepository<RecepcionUCL, Long>{
}
