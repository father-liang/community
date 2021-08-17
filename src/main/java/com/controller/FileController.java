package com.controller;

import com.dto.FileDTO;
import com.provider.AliCloudProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController {

    @Value("${alicloud.accesskey-id}")
    private String accesskeyId;

    @Value("${alicloud.accesskey-secret}")
    private String accesskeySecret;

    @Value("${alicloud.bucketName}")
    private String bucketName;

    @Value("${alicloud.endpoint}")
    private String endpoint;

    @Resource
    private AliCloudProvider aliCloudProvider;


    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        FileDTO fileDTO = new FileDTO();
        //上传文件到OSS库
        try {
            String fileName = aliCloudProvider.upload(accesskeyId, accesskeySecret, file.getOriginalFilename(), file.getInputStream(), bucketName, endpoint);
            fileDTO.setSuccess(1);
            fileDTO.setUrl(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileDTO;

    }
}
