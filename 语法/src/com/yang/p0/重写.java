package com.yang.p0;
class A
{
    public void fun() {
        System.out.println("父类fun调用");
    }
}

class B extends A
{
    public void fun(){
        System.out.println("子类fun调用");
    }
}

public class 重写 {
    public static void main(String arg[]){
        B b = new B();
        b.fun();
    }
}
