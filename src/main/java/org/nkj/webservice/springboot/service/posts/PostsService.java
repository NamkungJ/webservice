package org.nkj.webservice.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.nkj.webservice.springboot.domain.posts.Posts;
import org.nkj.webservice.springboot.domain.posts.PostsRepository;
import org.nkj.webservice.springboot.web.dto.PostsListResponseDto;
import org.nkj.webservice.springboot.web.dto.PostUpdateRequestDto;
import org.nkj.webservice.springboot.web.dto.PostsResponseDto;
import org.nkj.webservice.springboot.web.dto.PostsSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {  // 등록
        return postsRepository.save(requestDto.toEntity()).getId(); // Entity -> Domain
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {  // 수정
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());   // 더티 체킹

        return id;

    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public PostsResponseDto findById(Long id) { // id로 조회
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(posts); // Entity -> Dto
    }

}
