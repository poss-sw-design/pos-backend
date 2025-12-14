package com.pos.backend.repository;

import com.pos.backend.domain.employee.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
