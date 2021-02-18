package com.jpark.spring.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //1.선언된 모든 필드의 get 메소드를 생성
@RequiredArgsConstructor //2. 선언된 모든 final  필드가 포함된 생성자를 생성해 줌
                         //   final이 없는 필드는생성자에 포함되지 않음
public class HelloResponseDto {

    private final String name; //final 로 선언된 필드이기 때문에 생성자로 생성이 되면서 @Getter 때문에 get 메소드가 생성이됨
    private final int amount;
}
