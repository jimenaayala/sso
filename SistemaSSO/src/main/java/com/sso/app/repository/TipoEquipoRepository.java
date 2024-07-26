package com.sso.app.repository;

import com.sso.app.entity.TipoEquipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoEquipoRepository extends CrudRepository<TipoEquipo,Long> {

    @Query("SELECT e FROM TipoEquipo e WHERE e.modelo LIKE %:modelo%")
    List<TipoEquipo> findByModelo(String modelo);

}
