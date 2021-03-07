package com.jpark.spring.web;

import com.jpark.spring.web.dto.HelloResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//컨트롤러를 json을 반환하는 컨트롤러로 만들어줌
//예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다고 생각하면 됨
public class HelloController {
    @GetMapping("/hello")
    //HTTP 메소드인 Get의 요청을 받을수 있는 API를 만들어 줌
    //예전에는 @RequestMapping @GetMapping 똑같이 Get의 요청을 받지만
    //@RequestMapping는 클래스에 , @GetMapping는 메소드에서 사용한다


    public String hello(){
        return "hello";
        // 이 프로젝트는 /hello 로 요청이 들어오면 문자열 hello를 반환하는 기능을 가지게 됨
    }

    @GetMapping("/hello/dto")
    //@RequestParam 외부에서 api로 넘긴 파라미터를 가져오는 어노테이션
    //여기서는 외부에서 name (@RequestParam("name"))이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하게 됨
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
