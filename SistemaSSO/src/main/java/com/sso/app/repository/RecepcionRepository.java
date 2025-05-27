package com.sso.app.repository;

import com.sso.app.entity.Recepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepcionRepository extends JpaRepository<Recepcion, Long> {

}
