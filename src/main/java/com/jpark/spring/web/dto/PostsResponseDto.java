package com.jpark.spring.web.dto;

import com.jpark.spring.domain.posts.Posts;
import lombok.Getter;

@Getter
//PostsResponseDto는 Entity의 필드중 일부만 사용하므로 생성자로 Entity를 받아 필드에 값 입력
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        //생성자로 Dto는 Entity를 받아서 처리함 모든 필드를 가진 생선자가 필요 x
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
