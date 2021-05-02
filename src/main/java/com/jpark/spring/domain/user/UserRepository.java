package com.jpark.spring.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);//null 포인트 익셉션을 대항하기 위한 만든 클래스 Optional  클래스를 감싸는 wrapper
                                             //findByEmail-> 소셜로그인으로 반환된느 값 중 email을 통해 이미 생성된 사용자인지
                                             //처음 가입하는 사용자인지 판단하기 위한 메소드
}
