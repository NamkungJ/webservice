package org.nkj.webservice.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nkj.webservice.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter // getter 함수 추가
@NoArgsConstructor  // 디폴트 생성자 추가
@Entity // Posts 클래스는 테이블과 매칭되는 클래스
public class Posts extends BaseTimeEntity {

    @Id // PK 컬럼임
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, GenerationType.IDENTITY => auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 컬럼, 선언하지 않아도 Posts 클래스의 모든 필드는 컬럼이 됨 => 기본값 외에 추가 변경이 필요한 경우 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
