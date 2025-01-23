package com.sso.app.repository;

import com.sso.app.entity.Inspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InspeccionRepository<T extends Inspeccion> extends JpaRepository<T, Long> {

    @Query("SELECT i FROM Inspeccion i WHERE i.eliminado = false")
    List<Inspeccion> findAllActive(); //revisar ahora con herencia
}
