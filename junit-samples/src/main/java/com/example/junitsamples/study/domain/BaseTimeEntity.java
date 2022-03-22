package com.example.junitsamples.study.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @MappedSuperclass
 *   JPA Entity 클래스들이 이 클래스를 상속할 경우, 이 필드(createdDt, updatedDt)들도 컬럼으로 인식하도록 한다.
 * @EntityListeners(AuditingEntityListener.class)
 *   이 클래스에 Auditing 기능을 포함시킨다.
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    /**
     * @CreatedDate
     *   Entity 가 생성되어 저장될 때, 시간이 자동 저장된다.
     */
    @CreatedDate
    private LocalDateTime createdDt;

    /**
     * @LastModifiedDate
     *   조회한 Entity 의 값을 변경할 때, 시간이 자동 저장된다.
     */
    @LastModifiedDate
    private LocalDateTime updatedDt;
}
