package com.controller;

import com.mapper.QuestionMapper;
import com.model.Question;
import com.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String publish() {
        return "publish";
    }

    @PostMapping(value = "/publish")
    public String doPublish(Question question,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());

        if (question.getTitle() == null || question.getTitle() == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if (question.getDescription() == null || question.getDescription() == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }

        if (question.getTag() == null || question.getTag() == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        /*
         * 我这里使用的是Session中的user提取用户的Id
         * 视频里面采取的是获取Cookie，然后查询数据库
         * */

        User user = null;
        user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            //出现异常，未登录，返回发布页面
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";

    }

}
