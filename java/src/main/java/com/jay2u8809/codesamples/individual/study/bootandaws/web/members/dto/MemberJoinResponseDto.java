package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import lombok.Getter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Getter
public class MemberJoinResponseDto implements Serializable {

    private Long memberSn;

    private String memberId;

    public MemberJoinResponseDto(Member entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
