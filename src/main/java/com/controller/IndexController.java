package com.controller;

import com.mapper.UserMapper;
import com.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Resource
    private UserMapper userMapper;

    @GetMapping(value = "/")
    public String Index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        /*
        * 检查前端页面发来的Cookie，查看token的Cookies
        * 根据此token的值查询数据库，获得User对象，并将User对象放入到的Session中
        * */
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        return "index";
    }
}
