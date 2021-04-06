package com.jpark.spring.web;

import com.jpark.spring.domain.posts.Posts;
import com.jpark.spring.domain.posts.PostsRepository;
import com.jpark.spring.service.posts.PostsService;
import com.jpark.spring.web.dto.PostsSaveRequestDto;
import com.jpark.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RequiredArgsConstructor
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// WebMvcTest는 사용하지 않음 -> jpa가 작동하지 않
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private PostsRepository postsRepository;
    // 인터페이스 자체를 주입받을수는 없지만 해당 인터페이스가 JpaRepository를 상속 받았기 때문에 jpa레파지토리로 인해
    // 구현체(객체)가 생성이 되고 빈으로 자동으로 주입이됨
    // 그래서 인터페이스를 Autowired를 할수 있음 (따로 구현체를 만들지 않아도)

    @Autowired
    private TestRestTemplate restTemplate;

    //private final TestRestTemplate restTemplate;
    //private final PostsRepository postsRepository;
    
    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }
    
    @Test
    public void Posts_등록된다() throws Exception{
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts";
        
        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);
        
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        System.out.println(all.get(0).getId());
    }

    @Test
    public void Posts_수정된다() throws Exception{
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                                            .title("title")
                                            .content("contenct")
                                            .author("author")
        .build());
        System.out.println(savedPosts.getTitle());
        System.out.println(savedPosts.getId());

        Long updateId = savedPosts.getId();
        //등록(save) 된 id(entity 에 등록된 pk)가져옴 (pk가 long 타입)
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        System.out.println(requestDto.getTitle());

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        System.out.println("when : " + responseEntity.getBody());

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }

}
