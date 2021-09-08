package org.nkj.webservice.springboot.web;

import lombok.RequiredArgsConstructor;
import org.nkj.webservice.springboot.service.posts.PostsService;
import org.nkj.webservice.springboot.web.dto.PostsUpdateRequestDto;
import org.nkj.webservice.springboot.web.dto.PostsResponseDto;
import org.nkj.webservice.springboot.web.dto.PostsSaveRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // 등록 : json형태의 요청을 받아서 데이터를 수정 (json -> Dto -> Entity)
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // 수정 : json형태의 요청을 받아서 데이터를 수정 (json -> Dto -> 더티 체킹)
    @PutMapping("api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // Id로 조회 : json 형태로 응답 (json <- Dto <- Entity)
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    // 삭제
    @DeleteMapping("/api/v1/posts/{id}")
    public Long deleteById(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
