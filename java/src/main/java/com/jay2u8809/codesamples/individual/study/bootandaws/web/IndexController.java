package com.jay2u8809.codesamples.individual.study.bootandaws.web;

import com.jay2u8809.codesamples.individual.study.bootandaws.service.posts.PostsService;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller("individual.study.bootandaws.IndexController")
public class IndexController {

    private static final String BASIC_PATH = "/individual/study/bootandaws/";

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("posts", postsService.findAllDesc());

        // prefix: /resources/templates/
        // postfix : .mustache
        return BASIC_PATH + "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {

        return BASIC_PATH + "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return BASIC_PATH + "posts-update";
    }

}
