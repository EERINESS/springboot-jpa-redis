package com.zzq.controller;

import com.zzq.domain.Department;
import com.zzq.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzq
 * @createTime 2018/3/9
 */
@RestController
@RequestMapping("/dep")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/get/{id}")
    public Department get(@PathVariable Long id){
        return departmentService.findById(id);
    }

    @GetMapping("/save")
    public Department save(Department department){
        return departmentService.create(department);
    }
}
