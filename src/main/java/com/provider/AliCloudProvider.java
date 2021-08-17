package com.provider;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.exception.CustomizeErrorCode;
import com.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class AliCloudProvider {


    public String upload(String accesskeyId, String accesskeySecret, String fileName, InputStream inputStream, String bucketName, String endpoint){

        String generateFileName = "";
        String objectName = "";
        String[] filePaths = fileName.split("\\.");

        if (filePaths.length > 1){
            generateFileName = UUID.randomUUID().toString() +"."+ filePaths[filePaths.length - 1];

            if (filePaths[filePaths.length-1].equals("jpg") || filePaths[filePaths.length-1].equals("png")){
                objectName = "images/" + generateFileName;
            }else {
                objectName = "other/" + generateFileName;
            }
        }else{
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }


        OSS ossClient = new OSSClientBuilder().build(endpoint, accesskeyId, accesskeySecret);

        PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

        if (putObjectResult.getResponse() == null){
            int limitSpeed = 100 * 1024 * 8;

            Date date = new Date();
            //过期时间设置为十年
            date.setTime(date.getTime() + 60 * 1000 * 60 * 24 * 365 * 10);
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
            request.setExpiration(date);
            request.setTrafficLimit(limitSpeed);
            URL signedUrl = ossClient.generatePresignedUrl(request);
            return signedUrl.toString();
        }else {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
    }
}
