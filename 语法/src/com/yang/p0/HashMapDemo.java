package com.yang.p0;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String argv[]){
        Map<Integer,String> m = new HashMap<Integer, String>();
        m.put(1,"张三");
        m.put(2,"李四");

        String name = m.get(1);
        System.out.println(name);
    }
}
