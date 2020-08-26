package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet
{
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest()
    {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");

    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        HttpGet get = new HttpGet(testUrl);

        HttpClient client = null;
        this.store = new BasicCookieStore();
        client = HttpClientBuilder.create().setDefaultCookieStore(this.store).build();

//        CloseableHttpClient client = HttpClients.createDefault();
//        CloseableHttpResponse response = client.execute(get);
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        // 获取cookies信息

        List<Cookie> cookieList = this.store.getCookies();

        for(Cookie cookie: cookieList)
        {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println( "cookies_key " + name + " " + "cookie_value " + value);
        }

    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String uri = bundle.getString("test.get.cookies");
        String testUrl = this.url + uri;
        String result;

        HttpGet get = new HttpGet(testUrl);

//        CloseableHttpClient client = HttpClients.createDefault();
//        CloseableHttpResponse response = client.execute(get);
        HttpClient client = null;
        client = HttpClientBuilder.create().setDefaultCookieStore(this.store).build();
        HttpResponse response = client.execute(get);

        //获取状态码
        int status = response.getStatusLine().getStatusCode();
        System.out.println(status);

        if(status == 200)
        {
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
        else
            {
                System.out.println("没有携带cookie方法");
            }


    }
}
