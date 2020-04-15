package com.coco.hhz_crm_plus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coco.hhz_crm_plus.entity.Customer;
import com.coco.hhz_crm_plus.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customerList")
    public List<Customer> roles(){

        return customerService.list();
    }
    /**
     *  查询一页员工信息
     * @param page 当前页码
     * @param limit 每页记录数
     * @return
     */
    @GetMapping("/customer")
    public Map getList(Integer page,Integer limit ){
        Map<String,Object> map = new HashMap<String,Object> ();

        //设置mybatisPlus分页
        Page<Customer> p = new Page<Customer>();
        p.setSize(limit);       //设置每页记录数
        p.setCurrent(page);     //设置当前页码

        IPage<Customer> iPage = customerService.selectList(p);

        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }

    @GetMapping("/customer/pool")
    public Map customerPool(Integer page,Integer limit ){
        Map<String,Object> map = new HashMap<String,Object> ();
        Page<Customer> p = new Page<Customer>();
        p.setSize(limit);
        p.setCurrent(page);
        IPage<Customer> iPage = customerService.selectList(p);
        map.put("msg","查询情况");
        map.put("count",iPage.getTotal());
        map.put("data",iPage.getRecords());
        map.put("code",0);
        return map;
    }

    /**
     *  新增用户
     */
    @PostMapping("/customerAdd")
    public Map add(Customer customer,Integer empId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",customerService.add(customer,empId));
        return result;
    }

    /**
     *  删除用户
     */
    @DeleteMapping("/customer/del/{customerId}")
    public Map del(@PathVariable Integer customerId){
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setIsDel(1);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",customerService.updateById(customer));
        return result;
    }

    /**
     *  修改用户
     */
    @PutMapping("/customerUpdate")
    public Map edit(Customer customer,Integer empId){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("state",customerService.update(customer,empId));
        return result;
    }

}