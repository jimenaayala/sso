package com.sso.app.repository;

import com.sso.app.entity.Equipo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo,Long> {

}
