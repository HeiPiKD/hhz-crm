package com.coco.hhz_crm_plus.config;

import com.coco.hhz_crm_plus.shiro.EmpRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }


    @Bean
    public EmpRealm empRealm(){
        EmpRealm empRealm = new EmpRealm();
        empRealm.setCredentialsMatcher(hashedCredentialsMatcher() );
        return empRealm;
    }
    @Bean
    public DefaultWebSecurityManager securityManager(EmpRealm empRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(empRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        //实例化一个过滤器bean，设置他的安全管理器
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
        filterBean.setSecurityManager(securityManager);

        //*************过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢

        //实例化一个map对象用于存储过滤规则
        Map<String,String> filterMap = new LinkedHashMap<String,String>();

        //允许未授权（匿名）可访问  【配置不会被拦截的链接 顺序判断】
        filterMap.put("/doLogin","anon");   //允许登录判断url
        //这里的/static指的是url的static  【resource/static/static/**】
        filterMap.put("/static/**","anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterMap.put("/logout", "logout");

        //待会需要给url设置权限 （写完realm的授权之后）
        filterMap.put("/emp", "perms[emp:list]");


        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterMap.put("/**", "authc");
        //*************过滤链定义结束

        //设置登录的url，成功之后的url，没权限url
        filterBean.setLoginUrl("/login.html");  //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        filterBean.setSuccessUrl("/");
        filterBean.setUnauthorizedUrl("/403");  //401、403 没权限的状态码

        //将定义过滤器mapper放到过滤器对象中，由安全管理器来管理
        filterBean.setFilterChainDefinitionMap(filterMap);
        return filterBean;
    }
}
