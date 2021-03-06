package com.advice;

import com.alibaba.fastjson.JSON;
import com.dto.ResultDTO;
import com.exception.CustomizeErrorCode;
import com.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response){
        String contentType = request.getContentType();

        if("application/json".equals(contentType)){
            ResultDTO resultDTO = null;
            //返回 json
            if(e instanceof CustomizeException){
                resultDTO =  ResultDTO.errorOf((CustomizeException)e);
            }else{
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            return null;
        }else {
            //错误页面跳转

            if(e instanceof CustomizeException){
                model.addAttribute("message", e.getMessage());
            }else{
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }

    }

}
