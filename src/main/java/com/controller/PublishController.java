package com.controller;

import com.mapper.QuestionMapper;
import com.model.Question;
import com.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Resource
    private QuestionMapper questionMapper;

    @GetMapping(value = "/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping(value = "/publish")
    public String doPublish(Question question,
                            HttpServletRequest request){
        /*
        * 我这里使用的是Session中的user提取用户的Id
        * 视频里面采取的是获取Cookie，然后查询数据库
        * */
        User user = (User) request.getSession().getAttribute("user");

        question.setCreator(user.getId());
        questionMapper.create(question);
        return "publish";
    }

}
