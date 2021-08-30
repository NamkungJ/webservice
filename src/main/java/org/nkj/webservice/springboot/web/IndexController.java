package org.nkj.webservice.springboot.web;

import lombok.RequiredArgsConstructor;
import org.nkj.webservice.springboot.service.posts.PostsService;
import org.nkj.webservice.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    // 메인 페이지로 이동 > 글 전체 조회
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    // 글 등록 페이지 이동
    @GetMapping("/posts/save")
    public String postsSave() { // 글 등록 페이지로 이동
        return "posts-save";
    }

    // 글 수정 페이지 이동
    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }


}
