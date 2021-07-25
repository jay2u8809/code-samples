package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto;

import com.jay2u8809.codesamples.common.dto.EmbeddedAddress;
import com.jay2u8809.codesamples.common.dto.EmbeddedName;
import com.jay2u8809.codesamples.common.dto.EmbeddedTel;
import com.jay2u8809.codesamples.common.utils.EncryptUtils;
import com.jay2u8809.codesamples.common.validator.CommonValidator;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.MemberAccountInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class MemberSaveRequestDto implements Serializable {

    private Long memberSn;
    private String memberId;
    private String memberPw;
    private String memberName1;
    private String memberName2;
    private String memberName3;
    private String memberName4;
    private String memberEmail;
    private String nickName;
    private String zipCode;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String phoneNo;

    public Member saveMember () {
        Member member = new Member();
        member.setMemberId(this.memberId);
        member.setMemberPw(EncryptUtils.encryptSha256(this.memberPw));
        member.setMemberName(new EmbeddedName(this.memberName1, this.memberName2, this.memberName3, this.memberName4));
        member.setEmailAddress(ObjectUtils.isEmpty(this.memberEmail) ? "" : this.memberEmail);
        member.setMemberAddress(new EmbeddedAddress(null, this.zipCode, this.address1, this.address2, this.address3, this.address4));
        member.setPhoneNo1(new EmbeddedTel(this.phoneNo));

        MemberAccountInfo accountInfo = new MemberAccountInfo();
        accountInfo.setNickname(this.nickName);
        accountInfo.setSignupDt(LocalDateTime.now());
        accountInfo.setPwVerifyKey("VerifyKey_Dummy");
        member.setMemberAccountInfo(accountInfo);

        return member;
    }

    @Component
    public static class Validator extends CommonValidator {

        @Override
        public boolean supports(Class<?> clazz) {
            return MemberSaveRequestDto.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            MemberSaveRequestDto memberSaveRequestDto = (MemberSaveRequestDto) target;

//            checkMemberName(errors, memberSaveRequestDto.getMemberName1());
        }
    }
}
