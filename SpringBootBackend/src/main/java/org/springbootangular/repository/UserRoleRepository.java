package org.springbootangular.repository;

import org.springbootangular.model.RoleType;
import org.springbootangular.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(RoleType name);

}
