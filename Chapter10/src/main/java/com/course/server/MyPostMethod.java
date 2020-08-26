package com.course.server;


import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是我全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod
{
    private static Cookie cookie;

    //用户登录成功获取到cookies，然后访问其他接口获取到列表

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookie信息", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true)String password)
    {
        if (username.equals("lisi") && password.equals("l111111"))
        {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜您 登录成功";
        }
        return "用户名或密码错误，请联系管理员";


    }


    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u)
    {
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookies
        for (Cookie cookie: cookies)
        {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true") && u.getUsername().equals("lisi"))
            {
                User user = new User();
                user.setName("wangwu");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }
        return "参数不合法";
    }
}
