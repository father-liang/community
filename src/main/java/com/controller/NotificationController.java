package com.controller;

import com.dto.NotificationDTO;
import com.dto.PaginationDTO;
import com.enums.NotificationTypeEnum;
import com.mapper.NotificationMapper;
import com.model.Notification;
import com.model.User;
import com.service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Resource
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,@PathVariable(name = "id") Long id) {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id, user);

        if (notificationDTO.getType() == NotificationTypeEnum.REPLY_QUESTION.getType() ||
        notificationDTO.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()){
            return "redirect:/question/" + notificationDTO.getOuterid();
        }else{
            return "redirect:/";
        }
    }
}
