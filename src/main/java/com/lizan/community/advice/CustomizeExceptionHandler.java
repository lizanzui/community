package com.lizan.community.advice;


import com.lizan.community.dto.ResultDTO;
import com.lizan.community.exception.CustomizeErrorCode;
import com.lizan.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object exceptionHander(Exception e, Model model, HttpServletRequest request){
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            //返回json
            if (e instanceof CustomizeException) {
                return ResultDTO.errorOf((CustomizeException) e);
            } else {
                return ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
        } else {
            //错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("messages", e.getMessage());
            } else {
                model.addAttribute("messages", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            ModelAndView mv = new ModelAndView();
            mv.setViewName("error");
            return mv;
        }
    }
}


