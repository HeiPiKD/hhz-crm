package com.coco.hhz_crm_plus.until;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShiroUntilTest {

    @Test
    public void encryptPassword() {
        System.out.println("密码"+ShiroUntil.encryptPassword("123","小明63e4b5290272"));
    }

    @Test
    public void randomSalt() {
        System.out.println(ShiroUntil.randomSalt());
    }
}