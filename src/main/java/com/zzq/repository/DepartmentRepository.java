package com.zzq.repository;

import com.zzq.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zzq
 * @createTime 2018/3/8
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
