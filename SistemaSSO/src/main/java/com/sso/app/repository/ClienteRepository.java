package com.sso.app.repository;

import com.sso.app.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente,Long> {

    Optional<Cliente> findByRazonSocialContaining(String razonSocial);

    List<Cliente> findByEliminadoFalse();

    Optional<Cliente> findByIdAndEliminadoFalse(Long aLong);

}
