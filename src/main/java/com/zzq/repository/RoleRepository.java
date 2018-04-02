package com.zzq.repository;

import com.zzq.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zzq
 * @createTime 2018/3/8
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
