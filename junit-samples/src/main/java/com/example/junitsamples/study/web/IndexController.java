package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import com.example.junitsamples.study.config.auth.LoginUser;
import com.example.junitsamples.study.config.auth.dto.SessionUser;
import com.example.junitsamples.study.service.posts.PostsService;
import com.example.junitsamples.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    private final HttpSession httpSession;

    @GetMapping(value = ApiEndPoint.IndexController.BASIC)
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", this.postsService.findAllDesc());
        // 파라미터(@LoginUser SessionUser user) 로 인해 불필요한 처리
//        SessionUser user = (SessionUser) this.httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
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

    @GetMapping(value = ApiEndPoint.IndexController.POSTS_UPDATE)
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = this.postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
