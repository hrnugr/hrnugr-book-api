package com.harunugur.repository.auth;

import com.harunugur.entity.auth.Role;
import com.harunugur.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
        Optional<Role> findByName(RoleType name);
}
