package com.jpark.spring.domain.posts;

import com.jpark.spring.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
//롬복을 이용하여 클래스 모든 필드의 Getter 메소드를 자동 생성
@NoArgsConstructor
//기본생성자 자동추가
//No Arguments (파라미터가 없다) Constructor( 생성자 ) 즉 파라미터 없는 생성자 --> public Posts(){} 와 같은 효과
@Entity //테이블과 링크될 클래스
        //해당클랙스는 entity 로 사용하겠다는 어노테이션 선언
        //Entity 클래스에서는 절대 setter 메소드를 만들지 않는다
public class Posts extends BaseTimeEntity { //실제 db의 테이블과 매칭될 클래스 ( 보통 Entity 클래스라고 한다 )
                     //JPA를 사용시 db 데이터에 작업할 경우 실제 쿼리를 날리기보다 이 Entity 클래스의 수정을 통해 작업함

    @Id // 해당 테이블의 'PK' 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //이값들을 자동으로 생성
    //PK 생성 규칙을 나타냄 ( 스프링부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야하만 auto increment가 됨
    private Long id;

    @Column(length = 500, nullable = false)
    //테이블의 컬럼을 나타냄
    //굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬림이 됨
    //문자열의 경우 VARCHAR(255)가 기본값
    //사이즈를 500 늘리고 싶거나 타입을 text로 변경하고 싶거나 등의 경우에 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    //사이즈를 500 늘리고 싶거나 타입을 text로 변경하고 싶거나 등의 경우에 사용
    private String content;

    private String author;

    @Builder
    //해당 클래스의 빌더 패턴 클래스 생성
    //생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함 빌더???
    public Posts(String title, String content, String author){

        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
