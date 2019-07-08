package com.yang.p0;
interface E {
    public void fun1();
}

interface F {
    public void fun2();
}

class G implements E,F{
    public void fun1(){
        System.out.println("在类G中实现了接口E中的fun1方法");
    };
    public void fun2(){
        System.out.println("在类G中实现了接口F中的fun2方法");
    };
}

public class 接口 {
    public static void main(String arg[]){
        G g = new G();
        g.fun1();
        g.fun2();
    }
}
