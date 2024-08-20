package com.sso.app.repository;

import com.sso.app.entity.Inspeccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InspeccionRepository extends CrudRepository<Inspeccion, Long> {

    @Query("SELECT i FROM Inspeccion i WHERE i.eliminado = false")
    List<Inspeccion> findAllActive();
}
