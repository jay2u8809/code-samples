package com.jay2u8809.codesamples.individual.study.bootandaws.web.posts.dto;

import com.jay2u8809.codesamples.common.validator.CommonValidator;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Getter
@NoArgsConstructor
public class PostsSaveRequestsDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestsDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }

    @Component
    public static class Validator extends CommonValidator {

        @Override
        public boolean supports(Class<?> clazz) {
            return PostsSaveRequestsDto.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            PostsSaveRequestsDto postsSaveRequestsDto = (PostsSaveRequestsDto) target;

            checkPostTitle(errors, postsSaveRequestsDto.getTitle());
        }
    }
}
