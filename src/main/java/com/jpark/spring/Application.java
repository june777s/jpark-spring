package com.jpark.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication 어노테이션으로 인하여
//   1. 스프링부터 자동ㅇ설정, 2. 스프링bean 읽기와 생성을 모두 자동으로 설정
//   @SpringBootApplication이 있는 위치부터 설정을 읽어 가기 때문에 이클래스는 항상 프로젝트의 상단에 위치해야함
public class Application {
    // 이클래스는 앞으로 만들 프로젝트의 메인클래스
    public static void main(String[] args){
        SpringApplication.run(Application.class,args); //내장 was 실행
        // main 메소드에서 실행하는 SpringApplication.run 으로 인하여 내장 was 실행
        // 별도의 외부was가 필요 없음
        // 스프링부트로 인하여 톰캣 설치를 할 필요가 없고 스프링 부트로 만들어진 jar 파일로 실행 가능
    }
}
