package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost
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
    public void testPostMethod() throws IOException
    {
        String result;

        String uri = bundle.getString("test.post.cookies");
        String testUrl = this.url + uri;

        HttpClient client = null;
        client = HttpClientBuilder.create().setDefaultCookieStore(this.store).build();

        HttpPost post = new HttpPost(testUrl);


        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");


        post.addHeader("Content-Type", "application/json");
        post.addHeader("Cookie", this.store.toString());

        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

        //获取结果

        JSONObject resultJson = new JSONObject(result);
        String success = (String) resultJson.get("huhansan");
        String status = (String) resultJson.get("status");

        Assert.assertEquals("success", success);
        Assert.assertEquals("1",status);

    }

}
