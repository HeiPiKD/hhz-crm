package com.coco.hhz_crm_plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coco.hhz_crm_plus.entity.Contact;

public interface ContactService extends IService<Contact> {
    IPage<Contact> listCmC(Page<Contact> page);
}


