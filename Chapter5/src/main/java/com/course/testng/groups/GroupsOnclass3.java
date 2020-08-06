package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnclass3
{
    public void teacher1()
    {
        System.out.println("class3中的teacher1");
    }
    public void teacher2()
    {
        System.out.println("class3中的teacher2");
    }
}
