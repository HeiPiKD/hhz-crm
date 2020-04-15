package com.coco.hhz_crm_plus.shiro;

import com.coco.hhz_crm_plus.entity.Employee;
import com.coco.hhz_crm_plus.entity.Permission;
import com.coco.hhz_crm_plus.entity.Role;
import com.coco.hhz_crm_plus.service.EmployeeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpRealm extends AuthorizingRealm {
    @Autowired
    private EmployeeService employeeService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权设置的方法");
        //获取当前主体，赋予权限 然后我们在过滤器做多个过滤，符合权限就可以访问
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Employee emp = (Employee) principalCollection.getPrimaryPrincipal();

        //通过遍历，将user对象中的角色和权限都打包到info对象中
        for (Role role:emp.getRoleList()){
            info.addRole(role.getRoleName());   //将角色名存到角色中
            for (Permission p : role.getPermissionList()){
                info.addStringPermission(p.getPermission());       //将数据表中的权限“user:add”存到info中
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入UserRealm认证方法");
        //拿到前端传过来的用户名
        String empName = (String) authenticationToken.getPrincipal();
        Employee emp = employeeService.selectByName(empName);
        if (emp == null){
            throw new UnknownAccountException("找不到这个用户");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                emp,
                emp.getPwd(),
                ByteSource.Util.bytes(emp.getCredentialsSalt()),  //强化盐 = empName + salt
                getName());
        return info;
    }
}
