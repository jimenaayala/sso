package com.sso.app.repository;

import com.sso.app.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Long> {
    @Query("SELECT c FROM Cliente c WHERE c.razonSocial LIKE %:razonSocial%")
    Optional<Cliente> findByRazonSocial(String razonSocial);
}
