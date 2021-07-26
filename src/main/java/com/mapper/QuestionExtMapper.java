package com.mapper;

import com.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);

}