package com.controller;

import com.dto.PaginationDTO;
import com.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Resource
    private QuestionService questionService;

    @GetMapping(value = "/")
    public String Index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        //questionDTO不仅有question的信息，还有发布人的user的信息
        PaginationDTO pagination = questionService.list(page, size);

        model.addAttribute("pagination", pagination);

        return "index";
    }
}
