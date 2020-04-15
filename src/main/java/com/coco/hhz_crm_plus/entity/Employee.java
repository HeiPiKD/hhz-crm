package com.coco.hhz_crm_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("employee")
public class Employee extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer empId;
    private String empName;
    private String pwd;
    private String salt;
    private Integer age;
    private String sex;
    private String phone;
    private String address;


    public String getCredentialsSalt(){
        return this.empName+this.salt;
    }
    @TableField(exist = false)
    private List<Role> roleList;
}
