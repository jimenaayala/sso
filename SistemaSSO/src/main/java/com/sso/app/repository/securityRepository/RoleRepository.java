package com.sso.app.repository.securityRepository;

import com.sso.app.entity.securityentity.RoleEntity;
import com.sso.app.entity.securityentity.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
