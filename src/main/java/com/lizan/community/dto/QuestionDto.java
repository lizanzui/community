package com.lizan.community.dto;

import com.lizan.community.model.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Integer id;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreat;
    private Long gmtModified;
    private User user;
}
