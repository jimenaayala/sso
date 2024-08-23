package com.sso.app.repository;

import com.sso.app.entity.Cliente;
import com.sso.app.entity.Ensayo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnsayoRepository extends CrudRepository<Ensayo, Long> {
    @Query("SELECT e FROM Ensayo e WHERE e.eliminado = false")
    List<Ensayo> findAllActive();
}
