package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import com.example.junitsamples.study.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController
 *   컨트롤러를 JSON 을 반환하는 컨트롤러로 만들어준다.
 *   ResponseBody 어노테이션을 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다.
 */
@RestController
public class StudyHomeController {

    @GetMapping(value = ApiEndPoint.StudyHomeController.HELLO)   // @RequestMapping(method = RequestMethod.GET) 와 같은 의미
    public String hello() {
        return "hello";
    }

    @GetMapping(value = ApiEndPoint.StudyHomeController.HELLO_DTO)
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
