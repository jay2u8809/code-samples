package com.jay2u8809.codesamples.individual.study.bootandaws.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller("individual.study.bootandaws.IndexController")
public class IndexController {

    private static final String BASIC_PATH = "/individual/study/bootandaws/";

    @GetMapping("/")
    public String index() {

        // prefix: /resources/templates/
        // postfix : .mustache
        return BASIC_PATH + "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {

        return BASIC_PATH + "posts-save";
    }
}
