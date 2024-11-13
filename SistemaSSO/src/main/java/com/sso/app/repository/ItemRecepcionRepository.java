package com.sso.app.repository;

import com.sso.app.entity.ItemRecepcion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRecepcionRepository extends CrudRepository<ItemRecepcion, Long> {
}