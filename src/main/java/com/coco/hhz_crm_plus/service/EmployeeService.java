package com.coco.hhz_crm_plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coco.hhz_crm_plus.entity.Employee;

public interface EmployeeService extends IService<Employee> {
    IPage<Employee> selectList(Page<Employee> page);
    Employee selectByName(String empName);

    Integer add(Employee employee,Integer roleId);
    Integer update(Employee employee,Integer roleId);
}
