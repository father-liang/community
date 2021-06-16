package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublishController {
    @RequestMapping(value = "/publish")
    public String publish(){
        return "publish";
    }

}
