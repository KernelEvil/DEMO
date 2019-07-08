package com.yang.p0;
abstract class C
{
    abstract public void fun();
}

class D extends C
{
    public void fun(){
        System.out.println("抽象方法fun的实现");
    }
}

public class 抽象类 {
    public static void main(String arg[]){
        D d = new D();
        d.fun();
    }
}
