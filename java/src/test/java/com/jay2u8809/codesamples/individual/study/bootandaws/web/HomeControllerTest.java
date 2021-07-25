package com.jay2u8809.codesamples.individual.study.bootandaws.web;

import com.jay2u8809.codesamples.individual.study.bootandaws.web.posts.dto.HelloResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello_return() throws Exception {
        String hello = "Hello";

        mockMvc.perform(get("/individual/study/bootandaws/test/"))
                .andExpect(status().isOk())                     // header Status, 200, 404 등의 상태를 검증
                .andExpect(content().string(hello));            // result 응답 본문의 내용을 검증, "hello"라는 값을 비교 검증
    }

    @Test
    public void HelloResponseDtoTest() {

        String name = "test";
        int amount = 1000;

        // When
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // Then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

    @Test
    public void helloDto_return() throws Exception {

        String name = "DTO";
        int amount = 5430;

        mockMvc.perform((get("/individual/study/bootandaws/hello/dto/")
                    .param("name", name)
                    .param("amount", String.valueOf(amount))))        // param은 String만 가능
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))        // JSON응답값을 필드별로 검증할수 있는 메소드, $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}