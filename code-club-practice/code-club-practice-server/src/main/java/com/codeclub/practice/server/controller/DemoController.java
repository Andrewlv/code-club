package com.codeclub.practice.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/practice/")
public class DemoController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
