package com.sso.app.repository;

import com.sso.app.entity.ensayo.EnsayoCougar;
import com.sso.app.entity.ensayo.EnsayoDv1;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsayoCougarRepository extends CrudRepository<EnsayoCougar, Long> {
}
