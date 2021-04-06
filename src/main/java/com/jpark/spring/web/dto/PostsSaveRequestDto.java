package com.jpark.spring.web.dto;

import com.jpark.spring.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//getter 자동 생성
@NoArgsConstructor
//기본생성자 자동추가
//No Arguments (파라미터가 없다) Constructor( 생성자 ) 즉 파라미터 없는 생성자 --> public Posts(){} 와 같은 효과
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    //@Builder : 위에서 설명했던 Builder 패턴을 자동으로 생성해주는데, builderMethodName에
    // 들어간 이름으로 빌더 메서드를 생성해준다. 나같은 경우, 혼동을 줄이기 위해 클래스 명과 동일하게 놔두고 Builder로 선언했다.
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public Posts toEntity() {
        return Posts.builder().title(title)
                              .content(content)
                              .author(author)
                              .build();
    }
}
