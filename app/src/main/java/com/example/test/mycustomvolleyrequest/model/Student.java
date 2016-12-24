package com.example.test.mycustomvolleyrequest.model;

/**
 * 作者：Chris
 * 邮箱：395932265@qq.com
 * 描述:
 *      姓名
 *      年龄
 *      城市
 *      简单的数据
 */
public class Student {

    private String name;
    private int age;
    private String city;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}
