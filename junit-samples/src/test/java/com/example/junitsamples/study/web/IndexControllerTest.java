package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import com.example.junitsamples.study.common.CommonExtends;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest extends CommonExtends {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void index_test() throws Exception {
        // given
        String url = ApiEndPoint.IndexController.BASIC;
        log.info(">>>> index_test() - request url: {}", url);

        // when
//        String htmlBody = this.restTemplate.getForObject(ApiEndPoint.IndexController.BASIC, String.class);
//        log.info(">>>> index_test() - request body: {}", htmlBody);
        this.mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
//        assertThat(htmlBody).contains("Start Spring boot web service");
    }

    @Test
    @WithMockUser(roles = "USER")
    public void post_save_test() throws Exception {
        // given
        String url = ApiEndPoint.IndexController.POSTS_SAVE;
        log.info(">>>> post_save_test() - request url: {}", url);

        // when
//        String htmlBody = this.restTemplate.getForObject(url, String.class);
//        log.info(">>>> post_save_test() - request body: {}", htmlBody);
        this.mvc.perform(MockMvcRequestBuilders.get(url))
                        .andExpect(MockMvcResultMatchers.status().isOk());

        // then
//        assertThat(htmlBody).contains("Register Post");
    }
}