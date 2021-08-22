package org.nkj.webservice.springboot.web.dto;

import lombok.Getter;
import org.nkj.webservice.springboot.domain.posts.Posts;

@Getter
public class PostsResponseDto { // 조회 결과 응답을 위한 Dto

    private Long id;
    private String title;
    private String content;
    private String author;

    // 일부만 조회할 수 있기 때문에 Entity를 파라미터로 받아서 값을 넣음
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
