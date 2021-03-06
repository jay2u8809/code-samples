package com.jay2u8809.codesamples.individual.study.bootandaws.web;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.HelloResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("individual.study.bootandaws.!A!HomeController")
public class HomeController extends CommonExtends {

    @GetMapping("/individual/study/bootandaws/test/")
    @ResponseBody
    public String hello() {
        return "Hello";
    }

    @GetMapping("/individual/study/bootandaws/hello/dto/")
    @ResponseBody
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {

        return new HelloResponseDto(name, amount);
    }
}
