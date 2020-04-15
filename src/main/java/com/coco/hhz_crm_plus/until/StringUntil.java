package com.coco.hhz_crm_plus.until;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUntil {
    public static String getNowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date date = new Date();
        return   df.format(date);
    }
}
