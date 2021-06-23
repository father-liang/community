package com.controller;

import com.dto.PaginationDTO;
import com.dto.QuestionDTO;
import com.mapper.QuestionMapper;
import com.mapper.UserMapper;
import com.model.Question;
import com.model.User;
import com.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionService questionService;

    @GetMapping(value = "/")
    public String Index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Cookie[] cookies = request.getCookies();
        /*
         * 检查前端页面发来的Cookie，查看token的Cookies
         * 根据此token的值查询数据库，获得User对象，并将User对象放入到的Session中
         * */
        if (cookies != null && cookies.length != 0) {
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
        }
        //questionDTO不仅有question的信息，还有发布人的user的信息
        PaginationDTO pagination = questionService.list(page, size);

        model.addAttribute("pagination", pagination);

        return "index";
    }
}
