package com.controller;

import com.dto.AccessTokenDTO;
import com.dto.GithubUser;
import com.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthorizeController {

    @Resource
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest httpServletRequest) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);

        if (user != null) {
            //登录成功，将请求得到的对象放入到session中
            httpServletRequest.getSession().setAttribute("user", user);
            //重定向回index页面
            return "redirect:/";
        } else {
            //登录失败，重新登录
            return "redirect:/";
        }

    }


}
