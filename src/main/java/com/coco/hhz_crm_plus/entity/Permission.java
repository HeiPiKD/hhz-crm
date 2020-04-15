package com.coco.hhz_crm_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @TableId(value = "perm_id", type = IdType.AUTO)
    private Integer permId;
    private String permName;
    private String url;
    private String permission;


}
