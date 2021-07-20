package com.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不要换个试试?"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复");
    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
