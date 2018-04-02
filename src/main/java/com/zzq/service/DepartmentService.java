package com.zzq.service;

import com.zzq.domain.Department;
import com.zzq.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author zzq
 * @createTime 2018/3/9
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     *
     * @param id
     * @return
     * 存取缓存，如果缓存没有就找数据库，如果数据库有就拿出来存入缓存
     */
    @Cacheable(value = "mysql:department", keyGenerator = "simpleKey")
    public Department findById(Long id){
        return departmentRepository.findOne(id);
    }

    /**
     *
     * @param department
     * @return
     * 修改缓存，如果有就修改缓存，如果没有就存入缓存
     */
    @CachePut(value = "mysql:department", keyGenerator = "objectId")
    public Department create(Department department){
        return departmentRepository.save(department);
    }

    /**
     *
     * @param department
     * @return
     * 如上述一样
     */
    @CachePut(value = "mysql:department", keyGenerator = "objectId")
    public Department update(Department department){
        return departmentRepository.save(department);
    }

    /**
     *
     * @param id
     * 删除缓存，从数据库中删除数据，如果数据库中有，则也必须删除
     */
    @CacheEvict(value = "mysql:department", keyGenerator = "simpleKey")
    public void delete(Long id){
        departmentRepository.delete(id);
    }

}
