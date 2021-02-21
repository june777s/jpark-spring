package com.jpark.spring.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
//얘는 왜 자동 임포트도 안됐을까...?
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)//테스트를 진행할때 Junit에 내장된 실행자 외에 다른 실행자를 실행시킴
                            //여기서는 SpringRunner라는 스프링 실행자를 사용
                            //스프링부트 테스트와 JUnit 사이에 연결자 역할을 함
@WebMvcTest(controllers = HelloController.class)
//여러 스프링 테스트 어노테이션 중, web(Spring MVC)에 집중할 수 있는 어노테이션
//선언할 경우 Controller,ControllerAdvice등 어노테이션 사용 가능
// service, Component, Repository 어노테이션은 사용 불가
public class
HelloControllerTest {

    @Autowired //스프링이 관리하는 빈을 주입
    private MockMvc mvc;// 웹 api를 테스트 할떄 사용 , 스프링 mvc테스트의 시작점
                        // 이클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통하여 /hello 주소로 HTTP GET 요청을 함
                .andExpect(status().isOk())// 체이닝 지원이 되어 여러 검증 기능이 이렇게 넣을수 있음 ex> .andExpect() <--이렇게
                                           // mvc.perform의 결과를 검증 ,HTTP Head의 Status를 검증
                                           // 흔히 알고 있는 200, 404, 500 등상태를 검증 여기서는 ok(200) 인지에 대한 검증
                .andExpect(content().string(hello));
                                            // mvc.perform의 결과 검증
                                            // 응답 본문의 내용 검증 값 비교 ( string 타입의 값)
    }
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name",name)//
                                                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) //
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}

