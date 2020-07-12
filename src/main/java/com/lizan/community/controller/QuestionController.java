package com.lizan.community.controller;

import com.lizan.community.dto.CommentCreateDTO;
import com.lizan.community.dto.CommentDTO;
import com.lizan.community.dto.QuestionDto;
import com.lizan.community.enums.CommentTypeEnum;
import com.lizan.community.exception.CustomizeErrorCode;
import com.lizan.community.exception.CustomizeException;
import com.lizan.community.service.CommentService;
import com.lizan.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id, Model model) {
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        QuestionDto questionDTO = questionService.getById(questionId);
//        List<QuestionDto> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(questionId, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(questionId);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
//        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
