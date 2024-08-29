package com.sso.app.repository;

import com.sso.app.entity.Salida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalidaRepository extends CrudRepository<Salida, Long> {

    @Query("SELECT s FROM Salida s WHERE s.eliminado=false")
    List<Salida> findAllActive();

}
