package com.course.testng.groups;

import org.testng.annotations.Test;


@Test(groups = "stu")
public class GroupsOnclass2
{
    public void stu1()
    {
        System.out.println("class2中的stu1");
    }

    public void stu2()
    {
        System.out.println("class2中的stu2");
    }
}
