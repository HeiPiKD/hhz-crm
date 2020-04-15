package com.coco.hhz_crm_plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coco.hhz_crm_plus.entity.CustEmp;
import com.coco.hhz_crm_plus.entity.Customer;
import com.coco.hhz_crm_plus.mapper.CustEmpMapper;
import com.coco.hhz_crm_plus.mapper.CustomerMapper;
import com.coco.hhz_crm_plus.service.CustomerService;
import com.coco.hhz_crm_plus.until.StringUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Autowired
    private CustEmpMapper custEmpMapper;

    public CustomerServiceImpl() {
    }

    @Override
    public IPage<Customer> selectList(Page<Customer> page) {
        return customerMapper.selectList(page);
    }

    @Override
    public Customer selectByName(String customerName) {
        return customerMapper.selectByName(customerName);
    }

    @Override
    public Integer add(Customer customer, Integer empId) {
        customer.setCreateTime(StringUntil.getNowTime());
        customer.setUpdateTime(customer.getCreateTime());  //获取创建时间，可以提高一点性能
        customer.setIsDel(0);
        int result =  customerMapper.insert(customer);
        int customerId = customer.getCustomerId();  //获取插入自增的id
        //将roleId和deptId一同插入到  员工与角色关系表
        CustEmp custEmp = new CustEmp(customerId,empId);
        custEmp.setCreateTime(StringUntil.getNowTime());
        custEmp.setUpdateTime(custEmp.getCreateTime());
        custEmp.setIsDel(0);
        custEmpMapper.insert(custEmp);
        return result;
    }

    /*@Override
    public Integer update(Customer customer, Integer empId) {
        return null;
    }*/


    @Override
    public Integer update(Customer customer, Integer empId) {
        customer.setCreateTime(StringUntil.getNowTime());
        int result = customerMapper.updateById(customer);
        CustEmp custEmp = new CustEmp(customer.getCustomerId(),empId);
        custEmp.setUpdateTime(StringUntil.getNowTime());
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("customer_id",customer.getCustomerId());
        custEmpMapper.update(custEmp,wrapper);
        return result;
    }
}