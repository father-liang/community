package com.mapper;

import com.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    public void create(Question question);
}
