package com.coco.hhz_crm_plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coco.hhz_crm_plus.entity.Department;
import com.coco.hhz_crm_plus.entity.Role;
import com.coco.hhz_crm_plus.mapper.DepartmentMapper;
import com.coco.hhz_crm_plus.service.DepartmentService;
import com.coco.hhz_crm_plus.until.StringUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public IPage<Department> selectList(Page<Department> page) {
        return departmentMapper.selectList(page);
    }

    @Override
    public Integer add(Department department) {
        department.setCreateTime(StringUntil.getNowTime());
        department.setUpdateTime(department.getCreateTime());
        department.setIsDel(0);
        int result = departmentMapper.insert(department);
//        int perId = role.getRoleId();//不知道有没有用
        Department department1 = new Department();
        department1.setCreateTime(StringUntil.getNowTime());
        department1.setUpdateTime(department.getCreateTime());
        department1.setIsDel(0);
        departmentMapper.insert(department1);
        return result;
    }

    @Override
    public Integer update(Department department, Integer deptId) {
        department.setUpdateTime(StringUntil.getNowTime());
        int result = departmentMapper.updateById(department);
        Department department1 = new Department();
        department1.setUpdateTime(StringUntil.getNowTime());
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("dept_id",department.getDeptId());
        departmentMapper.update(department1,wrapper);
        return result;
    }
}
