package org.nkj.webservice.springboot.web;

import lombok.RequiredArgsConstructor;
import org.nkj.webservice.springboot.service.posts.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { // 메인 페이지로 이동
        model.addAttribute("posts", postsService.findAllDesc());    // 글 전체 조회
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() { // 글 등록 페이지로 이동
        return "posts-save";
    }

}
