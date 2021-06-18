package com.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    //bio是用户描述
    private String bio;
}
