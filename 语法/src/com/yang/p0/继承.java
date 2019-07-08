package com.yang.p0;
class Person
{
    private String name;
    private int    age;

    public Person(String name, int age){
        this.name = name;
        this.age  = age;
        System.out.println("父类的构造方法");
    }

    public String getInfo(){
        return "姓名："+this.name+"，年龄："+this.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Student extends Person
{
    private String school;

    public Student(String name, int age, String school){
        super(name,age);
        this.setSchool(school);
        System.out.println("子类的构造方法");
    }

    public void print(){
        System.out.println(getInfo()+"，学校："+school);
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
public class 继承
{
    public static void main(String argv[]){
        Student s = new Student("张三",20,"烟台大学");
        s.print();
    }
}
