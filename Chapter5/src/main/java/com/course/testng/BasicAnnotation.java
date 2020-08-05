package com.course.testng;


import org.testng.annotations.*;

public class BasicAnnotation
{
    // 最基本的注解，用来把方法标记为测试的一部分
    @Test
    public void testCase1()
    {
        System.out.println("这是测试用例一");

    }

    @Test
    public void testCase2()
    {
        System.out.println("这是测试用例二");
    }

    @BeforeMethod
    public void beforeMethod()
    {
        System.out.println("这是在方法前运行");

    }

    @AfterMethod
    public void afterMethod()
    {
        System.out.println("这是在方法后运行的");
    }

    @BeforeClass
    public void beforeClass()
    {
        System.out.println("类之前运行");
    }

    @AfterClass
    public void afterClass()
    {
        System.out.println("类之后运行");
    }

    @BeforeSuite
    public void beforeSuite()
    {
        System.out.println("beforeSuite");
    }

    @AfterSuite
    public void afterSuite()
    {
        System.out.println("afterSuite");
    }
}
