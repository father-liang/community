package com.advice;

import com.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Throwable e, Model model){
        if(e instanceof CustomizeException){
            model.addAttribute("message", e.getMessage());
        }else{
            model.addAttribute("message", "服务冒烟了，请稍后再尝试");
        }
        return new ModelAndView("error");
    }

}
