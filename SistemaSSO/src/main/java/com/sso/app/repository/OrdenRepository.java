package com.sso.app.repository;

import com.sso.app.entity.Orden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends CrudRepository<Orden,Long> {
}
