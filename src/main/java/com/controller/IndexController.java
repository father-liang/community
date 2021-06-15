package com.controller;

import com.mapper.UserMapper;
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
    public String Index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
            }
        }

        return "index";
    }
}
