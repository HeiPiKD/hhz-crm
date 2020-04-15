package com.coco.hhz_crm_plus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coco.hhz_crm_plus.entity.Department;
import com.coco.hhz_crm_plus.entity.Role;
import com.coco.hhz_crm_plus.service.DepartmentService;
import com.coco.hhz_crm_plus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dept")
    public Map getList(Integer page, Integer limit){
        Map<String,Object> map = new HashMap<String, Object>();
        Page<Department> p = new Page<Department>();
        p.setSize(limit);
        p.setCurrent(page);
        IPage<Department> iPage = departmentService.selectList(p);
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }

    @PostMapping("/dept")
    public Map add(Department department){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("state",departmentService.add(department));
        return result;
    }

    @DeleteMapping("/dept/del/{deptId}")
    public Map del(@PathVariable Integer deptId){
        Department department = new Department();
        department.setDeptId(deptId);
        department.setIsDel(1);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",departmentService.updateById(department));
        return result;
    }

    @PutMapping("/dept")
    public Map edit(Department department,Integer deptId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",departmentService.update(department,deptId));
        System.out.println("1111111111113333333333333333"+result);
        return result;
    }
}
