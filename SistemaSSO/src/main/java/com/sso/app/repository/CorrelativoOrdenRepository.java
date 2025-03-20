package com.sso.app.repository;

import com.sso.app.entity.CorrelativoOrden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorrelativoOrdenRepository extends JpaRepository<CorrelativoOrden, Long> {
    Optional<CorrelativoOrden> findByTipo(String tipo);
}
