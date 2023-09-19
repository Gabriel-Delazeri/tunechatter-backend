package com.delazeri.music.repositories;

import com.delazeri.music.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
