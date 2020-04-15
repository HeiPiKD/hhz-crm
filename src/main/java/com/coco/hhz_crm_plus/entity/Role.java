package com.coco.hhz_crm_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
public class Role extends BaseEntity {
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;
    private String roleName;
    @TableField(exist = false)
    private List<Department> departmentList;
    @TableField(exist = false)
    private List<Permission> permissionList;
}
