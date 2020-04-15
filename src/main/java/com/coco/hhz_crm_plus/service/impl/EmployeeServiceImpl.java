package com.coco.hhz_crm_plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coco.hhz_crm_plus.entity.EmpRole;
import com.coco.hhz_crm_plus.entity.Employee;
import com.coco.hhz_crm_plus.mapper.EmpRoleMapper;
import com.coco.hhz_crm_plus.mapper.EmployeeMapper;
import com.coco.hhz_crm_plus.service.EmployeeService;
import com.coco.hhz_crm_plus.until.ShiroUntil;
import com.coco.hhz_crm_plus.until.StringUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmpRoleMapper empRoleMapper;

    @Override
    public IPage<Employee> selectList(Page<Employee> page) {
        return employeeMapper.selectList(page);
    }

    @Override
    public Employee selectByName(String empName) {
        return employeeMapper.selectByName(empName);
    }

    @Override
    public Integer add(Employee employee, Integer roleId) {
        employee.setSalt(ShiroUntil.randomSalt());
        //将密码设置为 加密后的密码（由ShiroUtils里面encryptPassword方法实现）
        employee.setPwd(ShiroUntil.encryptPassword(employee.getPwd(),employee.getCredentialsSalt()));
        //设置时间，idDel
        employee.setCreateTime(StringUntil.getNowTime());
        employee.setUpdateTime(employee.getCreateTime());  //获取创建时间，可以提高一点性能
        employee.setIsDel(0);

        int result =  employeeMapper.insert(employee);

        int empId = employee.getEmpId();  //获取插入自增的id
        //将empId和roleId一同插入到  员工与角色关系表
        EmpRole empRole = new EmpRole(empId,roleId);
        empRole.setCreateTime(StringUntil.getNowTime());
        empRole.setUpdateTime(empRole.getCreateTime());  //获取创建时间，可以提高一点性能
        empRole.setIsDel(0);

        empRoleMapper.insert(empRole);

        return result;
    }

    @Override
    public Integer update(Employee employee, Integer roleId) {
        //System.out.println("修改的员工信息："+employee);
        //判断用户是否输入密码，如果没有，获取的就是空字符串 ("")  就不修改密码
        if (!"".equals(employee.getPwd()) ){
            //从ShiroUtils类中随机生成盐
            employee.setSalt(ShiroUntil.randomSalt());
            //将密码设置为 加密后的密码（由ShiroUtils里面encryptPassword方法实现）
            employee.setPwd(ShiroUntil.encryptPassword(employee.getPwd(),employee.getCredentialsSalt()));
        }else {
            employee.setPwd(null);
        }
        //设置时间
        employee.setUpdateTime(StringUntil.getNowTime());
        //将信息更新到数据库中（空的属性不修改）
        int result =  employeeMapper.updateById(employee);


        EmpRole empRole = new EmpRole(employee.getEmpId(),roleId);
        empRole.setUpdateTime(StringUntil.getNowTime());
        AbstractWrapper wrapper = new QueryWrapper();
        wrapper.eq("emp_id",employee.getEmpId());
        empRoleMapper.update(empRole,wrapper);

        return result;
    }


}
