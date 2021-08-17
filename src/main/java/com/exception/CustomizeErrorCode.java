package com.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不要换个试试?"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请先登录"),
    SYS_ERROR(2004,"服务器冒烟了，请你稍后再尝试！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "你回复的评论不存在了，要不要换个试试?"),
    CONTENT_IS_EMPTY(2007, "输入的内容不能为空"),
    READ_NOTIFICATION_FAIL(2008, "不好意思，您读取的信息出现了错误"),
    NOTIFICATION_NOT_FOUND(2009, "不好意思，消息已经失踪了"),
    FILE_UPLOAD_FAIL(2010, "图片上传失败，请稍后重试");
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
