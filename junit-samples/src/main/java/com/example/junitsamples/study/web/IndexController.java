package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = ApiEndPoint.IndexController.BASIC)
    public String index() {
        /**
         * ViewResolver 가 src/main/resources/templates/index.mustache 로 처리, 반환한다.
         *   ViewResolver: URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자
         */
        return "index";
    }

    @GetMapping(value = ApiEndPoint.IndexController.POSTS_SAVE)
    public String postsSave() {
        return "posts-save";
    }
}
