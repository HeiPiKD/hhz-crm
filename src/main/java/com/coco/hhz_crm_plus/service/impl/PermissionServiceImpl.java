package com.coco.hhz_crm_plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coco.hhz_crm_plus.entity.Permission;
import com.coco.hhz_crm_plus.mapper.PermissionMapper;
import com.coco.hhz_crm_plus.service.PermissionService;
import com.coco.hhz_crm_plus.until.StringUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public IPage<Permission> selectList(Page<Permission> page) {
        return permissionMapper.selectList(page);
    }

    @Override
    public Integer add(Permission permission) {
        permission.setCreateTime(StringUntil.getNowTime());
        permission.setUpdateTime(permission.getCreateTime());
        permission.setIsDel(0);
        int result = permissionMapper.insert(permission);
        int perId = permission.getPermId();//不知道有没有用
        Permission permission1 = new Permission();
        permission1.setCreateTime(StringUntil.getNowTime());
        permission1.setUpdateTime(permission.getCreateTime());
        permission1.setIsDel(0);
        permissionMapper.insert(permission1);
        return result;
    }

    @Override
    public Integer update(Permission permission,Integer permId) {
        permission.setUpdateTime(StringUntil.getNowTime());
        int result = permissionMapper.updateById(permission);
        Permission permission1 = new Permission();
        permission1.setUpdateTime(StringUntil.getNowTime());
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("perm_id",permission.getPermId());
        permissionMapper.update(permission1,wrapper);
        return result;
    }
}
