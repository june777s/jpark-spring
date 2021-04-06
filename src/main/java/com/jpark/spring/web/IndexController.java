package com.jpark.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index()
    {
        return "index";
        //머스테치 스타터 덕분에 index를 반환할때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정
        // 예를들어서 경로가 src/main/resources/templates/index.mustache 인데
        // 여기서 앞 경로 : src/main/resources/templates/ 와
        //      뒤 확장자 : mustache를 자동으로 지정해준다는 말
        // 그래서 index 반환은 src/main/resources/templates/index.mustache 이것과 동일하게 전환되어
        // ViewResolver가 처리하게 됨
    }
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
