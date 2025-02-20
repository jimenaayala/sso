package com.sso.app.repository;

import com.sso.app.entity.ensayo.EnsayoDv1;
import com.sso.app.entity.ensayo.EnsayoMiniG;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsayoDv1Repository extends CrudRepository<EnsayoDv1, Long> {
}
