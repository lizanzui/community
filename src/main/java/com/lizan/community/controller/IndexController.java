package com.lizan.community.controller;


import com.lizan.community.Mapper.QuestionMapper;
import com.lizan.community.Mapper.UserMapper;
import com.lizan.community.dto.QuestionDto;
import com.lizan.community.model.User;
import com.lizan.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Model model){
        Cookie[] cookies = request.getCookies();
        response.addCookie(new Cookie("test","test"));
        if (cookies != null && cookies.length != 0){
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
        List<QuestionDto> questionList = questionService.list();
        model.addAttribute("question",questionList);
        return "index";
    }
}
