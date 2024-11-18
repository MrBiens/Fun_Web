package com.vn.sbit.SpringMVC.util;

import java.util.Random;

public class VerifyCode {
    public static String verifyCode() {
        Random rdRandom = new Random();

        String s1= rdRandom.nextInt(10)+"";
        String s2= rdRandom.nextInt(10)+"";
        String s3= rdRandom.nextInt(10)+"";
        String s4= rdRandom.nextInt(10)+"";
        String s5= rdRandom.nextInt(10)+"";
        String s6= rdRandom.nextInt(10)+"";
        return s1+s2+s3+s4+s5+s6;
    }
}
