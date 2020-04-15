package com.coco.hhz_crm_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer")
public class Customer extends BaseEntity{

    @TableId(type = IdType.AUTO)
    private Integer customerId;
    private String customerName;
    private String sex;
    private String telPhone;
    private String company;
    private String address;
    private Integer empId;
    private Integer isOrders;

    @TableField(exist = false)
    private List<Employee> employeeList;
}