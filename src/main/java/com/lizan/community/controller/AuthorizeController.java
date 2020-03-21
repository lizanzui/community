package com.lizan.community.controller;

import com.lizan.community.dto.AccessTokenDTO;
import com.lizan.community.dto.GithubUser;
import com.lizan.community.provide.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.cliend.id}")
    private String cliendId;
    @Value("${github.cliend.secert}")
    private String cliendSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(cliendId);
        accessTokenDTO.setClient_secret(cliendSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user != null){
            //登录成功写cookie和session
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }
}
