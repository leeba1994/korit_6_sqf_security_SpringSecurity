package com.study.SpringSecurity.controller;

import com.study.SpringSecurity.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public ResponseEntity<?> get() {

        System.out.println(testService.aopTest());
        testService.aopTest2("김준일", 31);
        testService.aopTest3("010-2480-4593", "부산시 사상구");

        return ResponseEntity.ok("확인");
    }
}
