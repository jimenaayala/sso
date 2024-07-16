package com.sso.app.repository;

import com.sso.app.entity.Cliente;
import com.sso.app.entity.Equipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo,Long> {

    @Query("SELECT e FROM Equipo e WHERE e.modelo LIKE %:modelo%")
    List<Equipo> findByModelo(String modelo);

}
