package com.coco.hhz_crm_plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coco.hhz_crm_plus.entity.Employee;
import com.coco.hhz_crm_plus.entity.Role;

public interface RoleService extends IService<Role> {
    IPage<Role> selectList(Page<Role> page);


    Integer add(Role role);
    Integer update(Role role,Integer roleId);
}
