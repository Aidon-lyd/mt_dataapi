package com.qianfeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试springboot项目
 */

@RestController
public class HelloWorldController {
    // http://127.0.0.1:8080/say
    @RequestMapping("/say")
    public String say(){
        return "this is my first spring boot project!!!";
    }

    @RequestMapping("/say1")
    public List<Integer> say1(){
        ArrayList a = new ArrayList<>();
        a.add(820); //[820, 932, 901, 934, 1290, 1330, 1320]
        a.add(932);
        a.add(901);
        a.add(934);
        a.add(1290);
        a.add(1330);
        a.add(1320);
        return a;
    }
}
