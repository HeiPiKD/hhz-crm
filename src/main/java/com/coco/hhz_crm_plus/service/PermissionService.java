package com.coco.hhz_crm_plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coco.hhz_crm_plus.entity.Permission;

public interface PermissionService extends IService<Permission> {
    IPage<Permission> selectList(Page<Permission> page);
    Integer add(Permission permission);
    Integer update(Permission permission,Integer permId);
}
