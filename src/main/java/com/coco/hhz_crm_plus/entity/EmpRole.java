package com.coco.hhz_crm_plus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpRole extends BaseEntity {
    private Integer empId;
    private Integer roleId;
}
