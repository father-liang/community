package com.mapper;

import com.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public void insert(User user);

    User findByToken(String token);
}
