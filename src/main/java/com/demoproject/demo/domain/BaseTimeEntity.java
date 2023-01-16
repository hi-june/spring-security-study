package com.demoproject.demo.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA Entity들이 BaseTimeEntity를 상속할 경우 필드들도 Column으로 인식하게 해줌
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity 클래스에 Auditing기능을 포함시킨다.
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
