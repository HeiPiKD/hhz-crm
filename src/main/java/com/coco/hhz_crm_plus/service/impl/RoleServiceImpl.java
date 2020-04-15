package com.coco.hhz_crm_plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coco.hhz_crm_plus.entity.Permission;
import com.coco.hhz_crm_plus.entity.Role;
import com.coco.hhz_crm_plus.mapper.RoleMapper;
import com.coco.hhz_crm_plus.service.RoleService;
import com.coco.hhz_crm_plus.until.StringUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public IPage<Role> selectList(Page<Role> page) {
        return roleMapper.selectList(page);
    }

    @Override
    public Integer add(Role role) {

        role.setCreateTime(StringUntil.getNowTime());
        role.setUpdateTime(role.getCreateTime());
        role.setIsDel(0);
        int result = roleMapper.insert(role);
//        int perId = role.getRoleId();//不知道有没有用
        Role role1 = new Role();
        role1.setCreateTime(StringUntil.getNowTime());
        role1.setUpdateTime(role.getCreateTime());
        role1.setIsDel(0);
        roleMapper.insert(role1);
        return result;
    }

    @Override
    public Integer update(Role role, Integer roleId) {
        role.setUpdateTime(StringUntil.getNowTime());
        int result = roleMapper.updateById(role);
        Role role1 = new Role();
        role1.setUpdateTime(StringUntil.getNowTime());
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("role_id",role.getRoleId());
        roleMapper.update(role1,wrapper);
        return result;
    }
}
