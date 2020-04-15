package com.coco.hhz_crm_plus.until;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

public class ShiroUntil {
    public static String encryptPassword(String password, String credentialsSalt){
        return new Md5Hash( password ,credentialsSalt,2).toHex().toString();
    }

    public static String randomSalt(){
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(6).toHex();
        return hex;
    }
}
