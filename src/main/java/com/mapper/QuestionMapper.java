package com.mapper;

import com.dto.QuestionDTO;
import com.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    public void create(Question question);

    public List<Question> list();


}
