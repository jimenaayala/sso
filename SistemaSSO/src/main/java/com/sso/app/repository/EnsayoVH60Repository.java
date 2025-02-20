package com.sso.app.repository;

import com.sso.app.entity.ensayo.EnsayoCougar;
import com.sso.app.entity.ensayo.EnsayoVh60;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnsayoVH60Repository extends CrudRepository<EnsayoVh60, Long> {
}
