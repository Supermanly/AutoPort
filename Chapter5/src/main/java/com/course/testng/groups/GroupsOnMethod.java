package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod
{
    @Test(groups = "server")
    public void test1()
    {
        System.out.println("服务端组");
    }

    @Test(groups = "server")
    public void test2()
    {
        System.out.println("服务端组");

    }

    @Test(groups = "client")
    public void test3()
    {
        System.out.println("客服端");
    }

    @Test(groups = "client")
    public void test4()
    {
        System.out.println("客服端");
    }

    @BeforeGroups("server")
    public void beforeGroupsOnServer()
    {
        System.out.println("这是服务端组运行之前运行的方法");
    }

    @AfterGroups("server")
    public void afterGroupsOnServer()
    {
        System.out.println("这是服务端运行之后运行的>>>>>>>>");
    }
}