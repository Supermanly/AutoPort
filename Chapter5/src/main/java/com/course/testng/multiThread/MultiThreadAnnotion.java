package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadAnnotion
{
    @Test(invocationCount = 10, threadPoolSize = 3)
    public void test()
    {
        System.out.println(1);
        System.out.printf("thread Id : %s%n", Thread.currentThread().getId());
    }
}
