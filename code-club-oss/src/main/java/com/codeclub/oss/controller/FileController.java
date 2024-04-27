package com.codeclub.oss.controller;

import com.codeclub.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件操作controller
 */
@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @Value("${storage.service.type}")
    private String storageType;

    @RequestMapping("/testGetAllBucket")
    public String testGetAllBucket() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/testNacos")
    public String testNacos() throws Exception {
        System.out.println(storageType);
        return storageType;
    }

    /**
     * 上传文件
     *
     * @param uploadFile
     * @param bucket
     * @param objectName
     * @return
     * @throws Exception
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        return fileService.uploadFile(uploadFile, bucket, objectName);
    }
}
