package org.nkj.webservice.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() { // 메인 페이지로 이동
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() { // 글 등록 페이지로 이동
        return "posts-save";
    }

}
