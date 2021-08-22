package org.nkj.webservice.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.nkj.webservice.springboot.domain.posts.Posts;
import org.nkj.webservice.springboot.domain.posts.PostsRepository;
import org.nkj.webservice.springboot.web.dto.PostUpdateRequestDto;
import org.nkj.webservice.springboot.web.dto.PostsResponseDto;
import org.nkj.webservice.springboot.web.dto.PostsSaveRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId(); // Entity -> Domain
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());   // 더티 체킹

        return id;

    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(posts); // Entity -> Dto
    }

}
