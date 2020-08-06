package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

import java.lang.reflect.Method;

public class DataProviderTest
{
    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age)
    {
        System.out.println("name = " + name + "; " + "age = " + age);
    }

    @DataProvider(name = "data")
    public Object[][] provoiderData()
    {
        Object [] [] o = new Object[][]{
                {"zhangsan", 10},
                {"lisi", 20},
                {"wangwu", 30}
        };
        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name, int age)
    {
        System.out.println("test111111 方法" + name + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age)
    {
        System.out.println("test222222 方法" + name + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method)
    {
        Object[][] result = null;

        if(method.getName().equals("test1"))
        {
            result = new Object[][]{
                    {"zhangsan", 20},
                    {"lisi", 60}
            };

        }
        else if(method.getName().equals("test2"))
        {
            result = new Object[][]{
                    {"wangwu", 30},
                    {"zhaoliu", 70}
            };

        }
        return result;

    }
}
