package com.jpark.spring.web;

import com.jpark.spring.service.posts.PostsService;
import com.jpark.spring.web.dto.PostsResponseDto;
import com.jpark.spring.web.dto.PostsSaveRequestDto;
import com.jpark.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
// 기존 스프링에서 생성자로 Bean 객체를 주입받는 방식과 동일
// final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConstructor가 대신 생성
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        //RequestBody -> HTTP 의 요청을 자바객체로 전달 받음
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
                       //@PathVariable -> url에 특정위치의 값을 받겠다 url안의 {id} 와 PathVariable의 id가 매핑이됨
        System.out.println("update");
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        System.out.println("response 호출");
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
