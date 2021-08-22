package org.nkj.webservice.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // Entity 클래들이 BaseTimeEntity를 상속하면 createDate, modifiedDate도 컬럼으로 인식하도록
@EntityListeners(AuditingEntityListener.class)  // Auditing 기능
public abstract class BaseTimeEntity {

    @CreatedDate    // Entity가 생성되어 저장될 때 시간이 자동 저장
    private LocalDateTime createDate;

    @LastModifiedDate   // 조회한 Entity 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedDate;

}
