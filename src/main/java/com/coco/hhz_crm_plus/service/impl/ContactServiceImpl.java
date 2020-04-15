package com.coco.hhz_crm_plus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coco.hhz_crm_plus.entity.Contact;
import com.coco.hhz_crm_plus.entity.Employee;
import com.coco.hhz_crm_plus.mapper.ContactMapper;
import com.coco.hhz_crm_plus.service.ContactService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements ContactService {
    @Autowired
    private ContactMapper contactMapper;
    @Override
    public IPage<Contact> listCmC(Page<Contact> page) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        return contactMapper.listCmC(page,employee.getEmpId());
    }
}