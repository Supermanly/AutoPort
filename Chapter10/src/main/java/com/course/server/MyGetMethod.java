package com.course.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@Api(value="/", description="这是我的get方法")
public class MyGetMethod
{
    @RequestMapping(value = "/get/Cookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取cookies", httpMethod = "GET")
    public String getCookies(HttpServletResponse response)
    {
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你 获取cookies信息成功";
    }

    /**
     * 要求客户端携带cookies 访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */

    @RequestMapping(value = "get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "是一个需要携带cookies信息才能访问的get请求", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies))
        {
            return "你必须携带cookies信息来";
        }
        for(Cookie cookie: cookies)
        {
            if(cookie.getName().equals("login") && cookie.getValue().equals("true"))
            {
                return "恭喜你 访问成功";
            }
        }
        return "您必须携带cookie 才能访问成功";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式 url：key=value
     * 我们来模拟获取商品列表
     */

    @RequestMapping(value = "/get/with/param", method = RequestMethod.GET)
    @ApiOperation(value = "开发一个需要携带参数才能访问的get请求", httpMethod = "GET")
    public Map<String, Integer> getList(@RequestParam Integer start,
                                        @RequestParam Integer end)
    {
        Map<String,Integer> myList = new HashMap<>();

        myList.put("Nike", 1000);
        myList.put("mac", 15000);
        myList.put("干脆面", 2);

        return myList;
    }

    /**
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */

    @RequestMapping(value = "/get/with/param/{start}/{end}")
    @ApiOperation(value = "第二种需要携带参数访问的get请求", httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end)
    {
        Map<String,Integer> myList = new HashMap<>();

        myList.put("Nike", 1000);
        myList.put("mac", 15000);
        myList.put("干脆面", 2);

        return myList;
    }
}
