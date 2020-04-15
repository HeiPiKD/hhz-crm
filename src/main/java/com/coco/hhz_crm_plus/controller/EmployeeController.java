package com.coco.hhz_crm_plus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coco.hhz_crm_plus.entity.Employee;
import com.coco.hhz_crm_plus.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp")
    public Map getList( Integer page, Integer limit ){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<Employee> p = new Page<Employee>();
        p.setSize(limit);
        p.setCurrent(page);
        IPage<Employee> iPage = employeeService.selectList(p);
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }
    @PostMapping("/emp")
    public Map add(Employee employee,Integer roleId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",employeeService.add(employee,roleId));
        return result;
    }

    /**
     *  删除用户
     */
    @DeleteMapping("/emp/del/{empId}")
    public Map del(@PathVariable Integer empId){
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employee.setIsDel(1);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",employeeService.updateById(employee));
        return result;
    }

    /**
     *  修改用户
     */
    @PutMapping("/emp")
    public Map edit(Employee employee,Integer roleId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",employeeService.update(employee,roleId));
        return result;
    }
}
