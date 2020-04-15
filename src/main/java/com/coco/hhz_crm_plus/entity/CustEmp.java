package com.coco.hhz_crm_plus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustEmp extends BaseEntity{

    private Integer customerId;

    private Integer empId;
}
