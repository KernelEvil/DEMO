package com.yang.test;

import com.yang.entity.CollectEn;
import com.yang.entity.DevEn;
import com.yang.utils.RedisUtils;
import net.sf.json.JSONArray;

import java.util.Arrays;
import java.util.Random;

public class RedisTest {
    public static void main(String arg[]){
        Random random = new Random();
        while (true)
        {
            CollectEn a= new CollectEn("0001","压力",random.nextInt(10000)*0.01+"",0);
            CollectEn b= new CollectEn("0002","温度",random.nextInt(10000)*0.01+"",0);

            RedisUtils.putCollectInfo("0002","0001",a);
            RedisUtils.putCollectInfo("0001","0001",b);

//            CollectEn c= new CollectEn("0003","真空",random.nextInt(10000)*0.01+"",0);
//            CollectEn d= new CollectEn("0004","流速",random.nextInt(10000)*0.01+"",0);
//
//            DevEn e = new DevEn("0002","真空泵", Arrays.asList(c,d));
//            RedisUtils.putDevInfo("0002",e);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            String str = RedisUtils.getAllInfo();
            JSONArray ja = JSONArray.fromObject(str);
            System.out.println(ja);
        }
    }
}
