package com.service;

import com.mapper.UserMapper;
import com.model.User;
import com.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;


    public void createOrUpdate(User user) {

        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
       if(users.size() == 0){
           //插入
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insert(user);
       }else {
           //修改
           User dbUser = users.get(0);

           User updateUser = new User();
           updateUser.setGmtModified(System.currentTimeMillis());
           updateUser.setAvatarUrl(user.getAvatarUrl());
           updateUser.setName(user.getName());
           updateUser.setToken(user.getToken());

           UserExample userExample = new UserExample();
            userExample.createCriteria().andIdEqualTo(dbUser.getId());
           userMapper.updateByExampleSelective(updateUser, userExample);
       }

    }
}
