package com.example.techthink.persistence.repository.role;

import com.example.techthink.persistence.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
//    @Query("select r from Role r where r.name = ?1")
//    Role findByName(String name);
}
