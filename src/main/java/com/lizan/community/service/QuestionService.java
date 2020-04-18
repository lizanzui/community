package com.lizan.community.service;


import com.lizan.community.Mapper.QuestionMapper;
import com.lizan.community.Mapper.UserMapper;
import com.lizan.community.dto.PaginationDTO;
import com.lizan.community.dto.QuestionDto;
import com.lizan.community.model.Question;
import com.lizan.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDto> questionDtos = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);
        }
        paginationDTO.setQuestions(questionDtos);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }
}
