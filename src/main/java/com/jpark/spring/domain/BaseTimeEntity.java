package com.jpark.spring.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createDate, modifiedDate) 도 칼럼으로 인식하도록 함
                    //즉 해당 클래스를 상속받는 Entity 클래스들이 해당 클래스의 필드 값도 컬럼으로 인식하도록 하는 애노테이션!!
@EntityListeners(AuditingEntityListener.class)  //BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다
public class BaseTimeEntity {

    @CreatedDate    //Entity가 생성되어 저장될때 시간이 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate   //조회한 Entity의 값을 변경할때 시간이 자동 저장
    private LocalDateTime modifiedDate;
    
}
