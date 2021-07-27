package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto;

import com.jay2u8809.codesamples.common.dto.EmbeddedName;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MembersResponseDto implements Serializable {

    private Long memberSn;

    private String memberId;

    private EmbeddedName memberName;

    private String emailAddress;

    public MembersResponseDto(Member entity) {
        this.memberSn = entity.getMemberSn();
        this.memberId = entity.getMemberId();
        this.memberName = entity.getMemberName();
        this.emailAddress = entity.getEmailAddress();
    }
}
