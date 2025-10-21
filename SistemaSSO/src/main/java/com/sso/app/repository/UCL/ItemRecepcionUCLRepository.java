package com.sso.app.repository.UCL;

import com.sso.app.entity.UCLRecepcion.ItemRecepcionUCL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRecepcionUCLRepository extends JpaRepository<ItemRecepcionUCL, Long> {
}
