package com.coco.hhz_crm_plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coco.hhz_crm_plus.entity.Department;
import com.coco.hhz_crm_plus.entity.Role;

public interface DepartmentService extends IService<Department> {
    IPage<Department> selectList(Page<Department> page);
    Integer add(Department department);
    Integer update(Department department,Integer deptId);
}
