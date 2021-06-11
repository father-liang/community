package com.controller;

import com.dto.AccessTokenDTO;
import com.provider.GithubProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;


@Controller
public class AuthorizeController {

    @Resource
    private GithubProvider githubProvider;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setClient_id("Iv1.9ea01843dcda5ae5");
        accessTokenDTO.setClient_secret("9fc035de734ebe1515ee96c105bfd40bd4a3421a");
        githubProvider.getAccessToken(accessTokenDTO);

        //登录成功之后，返回index页面
        return "index";
    }


}
