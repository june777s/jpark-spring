package com.jpark.spring.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//jpa 에서 사용하는 흔한 Dao 라고봐도 무방
@Repository
public interface PostsRepository extends JpaRepository<Posts,Long> {
// JpaRepository<엔티티 클래스 , pk 타입>로 상속하면 기본적인 crud 메소드가 자동생성
    // 도메인패키지( 예를들어서 Posts라는 엔티티와 레파지토리는 같은 패키지 안에 있어야함 서로 종속적인 관계
    // 규모가 커져도 같은 도메인 패키지 안에서 함께 관리가 되어야 함
}
