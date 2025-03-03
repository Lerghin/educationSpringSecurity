package com.lerdev.SpringSecurityEducation.repository;

import com.lerdev.SpringSecurityEducation.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
