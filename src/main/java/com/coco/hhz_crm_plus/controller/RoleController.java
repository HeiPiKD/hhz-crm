package com.coco.hhz_crm_plus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coco.hhz_crm_plus.entity.Permission;
import com.coco.hhz_crm_plus.entity.Role;
import com.coco.hhz_crm_plus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public Map getList(Integer page, Integer limit){
        Map<String,Object> map = new HashMap<String, Object>();
        Page<Role> p = new Page<Role>();
        p.setSize(limit);
        p.setCurrent(page);
        IPage<Role> iPage = roleService.selectList(p);
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }

    @PostMapping("/role")
    public Map add(Role role){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("state",roleService.add(role));
        return result;
    }

    @DeleteMapping("/role/del/{roleId}")
    public Map del(@PathVariable Integer roleId){
        Role role = new Role();
        role.setRoleId(roleId);
        role.setIsDel(1);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",roleService.updateById(role));
        return result;
    }

    @PutMapping("/role")
    public Map edit(Role role,Integer roleId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",roleService.update(role,roleId));
        System.out.println("1111111111113333333333333333"+result);
        return result;
    }
}
