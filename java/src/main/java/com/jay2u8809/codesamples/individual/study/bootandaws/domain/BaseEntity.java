package com.jay2u8809.codesamples.individual.study.bootandaws.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass       // JPA Entity 클래스들이 이 클래스를 상속할 경우 필드(created, modified)도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)  // Auditing 기능을 포함시킴
public abstract class BaseEntity {

    private static final long serialVersionUID = -1L;

    @CreatedBy
    @Column(name = "created_by", length = 50, updatable = false)
    private String createdBy;

    @CreatedDate        // Entity가 생성되어 저장될때 시간이 자동 저장된다.
    @Column(name = "created_dt", length = 50, updatable = false)
    private LocalDateTime createdDt;

    @LastModifiedBy
    @Column(name = "modified_by", length = 50)
    private String modifiedBy;

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
    @Column(name = "modified_dt", length = 50)
    private LocalDateTime modifiedDt;

    @PrePersist
    public void preInsert() {

        // JPA insert 하기 전 실행됨
        if (ObjectUtils.isEmpty(this.createdBy)) {
            this.createdBy = "admin";
        }
        if (ObjectUtils.isEmpty(this.modifiedBy)) {
            this.modifiedBy = "admin";
        }

        LocalDateTime now = LocalDateTime.now();
        if (this.createdDt == null) {
            this.createdDt = now;
        }
        if (this.modifiedDt == null) {
            this.modifiedDt = now;
        }
    }

    @PreUpdate
    public void preUpdate() {

        this.modifiedBy = "admin";

        LocalDateTime now = LocalDateTime.now();
        this.modifiedDt = now;

        // merge
        if (this.createdBy == null) {
            this.createdBy = "admin";
        }
        if (this.createdDt == null) {
            this.createdDt = now;
        }
    }
}
