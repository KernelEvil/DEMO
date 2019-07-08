package com.yang.p0;
class H {
    public void fun(){
        System.out.println("调用H类中的fun方法");
    }
}

class I extends H{
    public void fun(){
        System.out.println("调用I类中的fun方法");
    }
}

public class 向上转型 {
    public static void main(String arg[]){
        H h = new I();
        h.fun();
    }
}
