package com.coco.hhz_crm_plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.coco.hhz_crm_plus.mapper")
@SpringBootApplication
public class HhzCrmPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhzCrmPlusApplication.class, args);
    }

}
