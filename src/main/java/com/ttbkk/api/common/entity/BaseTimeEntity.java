package com.ttbkk.api.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//객체의 생성및변경 시간을 필요로 할때 상속받아 쓰는 클래스
//자동 DB 매핑 하여 시간 생성 & 초기화
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    /**
     * entity 의 생성된 시간을 저장합니다.
     */
    @CreatedDate
    @Column(name = "created_at", columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;

    /**
     * entity 의 최종 수정된 시간을 저장합니다.
     */
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "DATETIME(6)")
    private LocalDateTime updatedAt;

}


