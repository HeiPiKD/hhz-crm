package com.coco.hhz_crm_plus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeMapperTest {
@Autowired
    private EmployeeMapper employeeMapper;

@Test
    public void select(){
    System.out.println(employeeMapper.selectById(2));
}

    @Test
    public void selectList(){
//        System.out.println(employeeMapper.selectList());
    }

    @Test
    public void selectByName(){
        System.out.println(employeeMapper.selectByName("小明"));
    }
}