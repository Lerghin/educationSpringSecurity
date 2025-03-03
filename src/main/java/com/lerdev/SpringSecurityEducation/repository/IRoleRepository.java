package com.lerdev.SpringSecurityEducation.repository;

import com.lerdev.SpringSecurityEducation.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
