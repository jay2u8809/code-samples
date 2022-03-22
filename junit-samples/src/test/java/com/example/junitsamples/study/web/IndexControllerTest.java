package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import com.example.junitsamples.study.common.CommonExtends;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest extends CommonExtends {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void index_test() {
        // when
        String body = this.restTemplate.getForObject(ApiEndPoint.IndexController.BASIC, String.class);
        log.info(">>>> request body: {}", body);

        // then
        assertThat(body).contains("Start Spring boot web service");
    }
}