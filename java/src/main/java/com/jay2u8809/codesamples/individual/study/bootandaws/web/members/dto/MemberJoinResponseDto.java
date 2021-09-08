package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

@Getter
public class MemberJoinResponseDto implements Serializable {

    private final Long memberSn;
    private final String memberId;
    private final String memberName1;
    private final String memberName2;
    private final String memberName3;
    private final String memberName4;
    private final String memberEmail;
    private final String nickName;
    private final String zipCode;
    private final String address1;
    private final String address2;
    private final String address3;
    private final String address4;
    private final String phoneNo;

    public MemberJoinResponseDto(Member entity) {
        this.memberSn = entity.getMemberSn();
        this.memberId = entity.getMemberId();
        this.memberName1 = entity.getMemberName().getGivenName();
        this.memberName2 = entity.getMemberName().getSurName();
        this.memberName3 = entity.getMemberName().getGivenNameEn();
        this.memberName4 = entity.getMemberName().getSurNameEn();
        this.memberEmail = entity.getEmailAddress();
        this.nickName = ObjectUtils.isEmpty(entity.getMemberAccountInfo()) ? null : entity.getMemberAccountInfo().getNickname();
        this.zipCode = entity.getMemberAddress().getZipCode();
        this.address1 = entity.getMemberAddress().getAddress1();
        this.address2 = entity.getMemberAddress().getAddress2();
        this.address3 = entity.getMemberAddress().getAddress3();
        this.address4 = entity.getMemberAddress().getAddress4();
        this.phoneNo = entity.getPhoneNo1().getPhoneNo();
    }
}
