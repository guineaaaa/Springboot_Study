package com.apple.shopExample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Controller
public class BasicController {
    @GetMapping("/")
    // @ResponseBody -> 문자 그대로 보내주세요
    String hello(){
        return "index.html"; //기본 경로가 static 폴더
    }

    @GetMapping("/about")
    @ResponseBody
    String about(){
        return "피싱 사이트 에요";
    }



    @GetMapping("/date")
    @ResponseBody
    String date(){
        return ZonedDateTime.now().toString();
    }
}
