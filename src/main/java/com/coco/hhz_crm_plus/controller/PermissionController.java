package com.coco.hhz_crm_plus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coco.hhz_crm_plus.entity.Employee;
import com.coco.hhz_crm_plus.entity.Permission;
import com.coco.hhz_crm_plus.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController

public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/per")
    public Map getList(Integer page,Integer limit){
        Map<String,Object> map = new HashMap<String, Object>();
        Page<Permission> p = new Page<Permission>();
        p.setSize(limit);
        p.setCurrent(page);
        IPage<Permission> iPage = permissionService.selectList(p);
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }

    @PostMapping("/per")
    public Map add(Permission permission){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("state",permissionService.add(permission));
        return result;
    }
    @DeleteMapping("/per/del/{permId}")
    public Map del(@PathVariable Integer permId){
        Permission permission = new Permission();
        permission.setPermId(permId);
        permission.setIsDel(1);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",permissionService.updateById(permission));
        return result;
    }

    @PutMapping("/per")
    public Map edit(Permission permission,Integer permId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",permissionService.update(permission,permId));
        System.out.println("1111111111113333333333333333"+result);
        return result;
    }
}
