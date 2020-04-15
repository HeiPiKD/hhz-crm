package com.coco.hhz_crm_plus.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @PostMapping("/doLogin")
    public Map login(@RequestBody Map<String,String> map){
        System.out.println(map);
        Map<String,String> result = new HashMap<String, String>();

        result.put("code","ok");
        System.out.println(result);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(map.get("empName"),map.get("pwd"));
        try{
            subject.login(token);
        }catch (UnknownAccountException uae){
            result.put("code","用户名不存在");
        }catch (IncorrectCredentialsException ice){
            result.put("code","密码错误");
        }
        return result;
    }
}
