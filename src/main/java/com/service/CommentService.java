package com.service;

import com.exception.CustomizeErrorCode;
import com.exception.CustomizeException;
import com.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
