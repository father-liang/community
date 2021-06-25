package com.mapper;

import com.dto.QuestionDTO;
import com.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    public void create(Question question);

    public List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    public Integer count();


    @Select("select * from question where creator=#{userId} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param("userId") Integer userId);
}
