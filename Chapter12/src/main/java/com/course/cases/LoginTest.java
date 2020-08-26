package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginTest {


    @BeforeTest(groups = "loginTrue", description = "测试准备工作")
    public void beforeTest(){

        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
    }

    @Test(groups = "logintrue", description = "用户登录接口测试")
    public void loginTrue() throws IOException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase", 1);
//        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);


        //发送请求
        String result = getResult(loginCase);

        // 验证结果
        Assert.assertEquals(loginCase.getExpected(), result);

    }

    private String getResult(LoginCase loginCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName",loginCase.getUserName());
        param.put("password", loginCase.getPassword());

        post.setHeader("Content-Type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        String result;

//        CookieStore cookieStore = new BasicCookieStore();

        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
//        TestConfig.store.getCookies();
//        List<Cookie> cookies = TestConfig.store.getCookies();
//        TestConfig.store = (CookieStore) cookies;
        CloseableHttpResponse httpResponse = httpclient.execute(post);
        result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");

        System.out.println("=================" + TestConfig.store);

        return result;

    }

    @Test(groups = "loginFalse", description = "用户登录失败")
    public void loginFalse() throws IOException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        LoginCase loginCase = sqlSession.selectOne("loginCase", 2);
//        System.out.println(loginCase.toString());
//        System.out.println(TestConfig.loginUrl);

    }

}
