package com.jay2u8809.codesamples.individual.study.bootandaws.service.members;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto.MemberJoinRequestDto;

public interface MemberService {

    /**
     * Get One Member Info By Member Sn (All Info)
     * @param memberSn
     */
    default Member findMemberBySn(Long memberSn) {
        return new Member();
    };

    /**
     * Get One Member Info By User ID (All Info)
     * @param memberId
     */
    default Member findMemberById(String memberId) {
        return new Member();
    };

    /**
     * Register Member
     * @param joinRequestDto
     */
    default Long saveMember(MemberJoinRequestDto joinRequestDto) {
        return 0L;
    };


}
