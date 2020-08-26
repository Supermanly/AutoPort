package com.course.httpclient.demo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import sun.net.www.http.HttpClient;

import java.io.IOException;

public class MyHttpClient
{
    @Test
    public void test1() throws IOException {
        String result;
        HttpGet get = new HttpGet("https://www.doc88.com");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(get);

        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
}
