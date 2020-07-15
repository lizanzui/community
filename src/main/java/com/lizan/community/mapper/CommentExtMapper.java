package com.lizan.community.mapper;

import com.lizan.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}