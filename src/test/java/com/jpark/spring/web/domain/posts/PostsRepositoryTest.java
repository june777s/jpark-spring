package com.jpark.spring.web.domain.posts;

import com.jpark.spring.domain.posts.Posts;
import com.jpark.spring.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
//별다른 설정이 없다면 @SpringBootTest를 사용할 경우 H2 데이터베이스를 자동으로 실행
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    //Junit에서 단위테스트가 끝날떄마다 수행되는 메소드 지정
    //보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
    //여러테스트가 동시에 수행되면 테슽용 데이터베이스인 H2에 데이터가 그대로 남아 있어서 다음 테스트 실행시 테스트 실패 가능성 있음
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // JpaRepository를 상속 받은 postsRepository 인터페이스의 save 메소드는 테이블(엔티티) posts에 insert/update 쿼리를 실행
        // id 값이 있다면 update, 없다면 insert 쿼리 실행
        postsRepository.save(Posts.builder()
                                    .title(title)
                                    .content(content)
                                    .author("jpark@naver.com")
                                    .build());
        //when
        //postsRepository.findAll -> 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
        List<Posts> postList = postsRepository.findAll();
        // 모든 데이터를 가져와서 Post 엔티티(클래스) 콜렉션을 가지는 list에 삽입
        //System.out.println(postList.get(0).getId());

        //then
        Posts posts = postList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
