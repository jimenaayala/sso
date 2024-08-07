package com.sso.app.repository;

import com.sso.app.entity.Orden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenRepository extends CrudRepository<Orden,Long> {
    Optional<Orden> findByNumeroOT(String numeroOT);
    List<Orden> findByEliminadoFalse();
    List<Orden> findByEliminadoFalseAndActivaTrue();
}
