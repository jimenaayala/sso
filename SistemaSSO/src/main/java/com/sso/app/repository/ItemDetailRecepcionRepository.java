package com.sso.app.repository;

import com.sso.app.entity.ItemDetailRecepcion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDetailRecepcionRepository extends CrudRepository<ItemDetailRecepcion, Long> {
}
