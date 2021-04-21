package com.jpark.spring.service.posts;

import com.jpark.spring.domain.posts.Posts;
import com.jpark.spring.domain.posts.PostsRepository;
import com.jpark.spring.web.dto.PostsListResponseDto;
import com.jpark.spring.web.dto.PostsResponseDto;
import com.jpark.spring.web.dto.PostsSaveRequestDto;
import com.jpark.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
// 보통 빈을주입받는 방식은 @Autowired보다 생성자를 통하여 외부로부터 빈을 주입받는 방식을 사용한다
// 그렇다면 생성자는 어딨을까? 바로 롬복의 @RequiredArgsConstructor가 final 이 선언된 모든 필드를 인자값으로 생성자를 생성해줌
// ex> public PostsService postsService(PostsRepository postsRepository) {
//      this.postsRepository = postsRepository } 이것과 동일

// 이것을 사용하는 이유??? 왜냐하면 해당클래스의 의존성 관계가 변경될때마다 생성자 코드를 계속해서 수정하는 번거로움을 해결하기 위함!!!
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    /* 질문 1) 보통 인터페이스 변수에는 해당 인터페이스를 구현한 구현체의 클래스 객체를 주입하는게 일반적인데 이건 구현체 없는데 어떻게 주입이 되지?*/
    /*
    ex>1 보통 생성자를 통하여 외부로부터 빈을 주입받는 방식을 사용하였는데
    롬복의 @RequiredArgsConstructor가 이것을 대신함

    public PostsService(PostsRepository postsRepository){
        this.postsRepository=postsRepository;
    }

     */
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    //@Transactional 이거 선언 안하니까 update가 안돌더라....왜지? --> 데이타 정상 처리 후 commit 혹은 rollback 처리

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(),requestDto.getContent());
        System.out.println("service : " + id );
        return  id;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        //1.stream 은 다양한 데이타소스를 표준화된 방법을 다루기 위한것 stream() 데이타 컬렉션으로 배열이든, 리스트든 ,set이든 묶음
        //  또한 stream()을 통하여 중간 연산이 가능하다
        //2.map(클래스::메소드)
        //  여기서 .map(PostsListResponseDto::new) 는 .map(posts -> new PostsListResponseDto(posts) ) 처럼 람다식으로 표현가능\
        //3.collect 리스트와 타입이 다른 output을 내는 경우.(map 형식을 list 로)
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없음. id="
                                                                + id));
        postsRepository.delete(posts);

    }

    public PostsResponseDto findById(Long id) {
        Posts entity= postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }
}
