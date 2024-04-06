package com.codeclub.oss.controller;

import com.codeclub.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @RequestMapping("/testGetAllBucket")
    public String testGetAllBucket() throws Exception {
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }
}
