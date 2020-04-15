package com.coco.hhz_crm_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coco.hhz_crm_plus.entity.Contact;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactMapper extends BaseMapper<Contact> {
    IPage<Contact> listCmC(Page<Contact> page, Integer empId);
}
