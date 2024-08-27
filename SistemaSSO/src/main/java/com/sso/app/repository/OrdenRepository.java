package com.sso.app.repository;

import com.sso.app.entity.Orden;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenRepository extends CrudRepository<Orden,Long> {
    Optional<Orden> findByNumeroOT(String numeroOT);

    //query para el softDeleted para Orden
    @Query("SELECT o FROM Orden o WHERE o.eliminado = false")
    List<Orden> findAllActive();

}
