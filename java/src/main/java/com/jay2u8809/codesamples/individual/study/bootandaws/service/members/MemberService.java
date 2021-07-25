package com.jay2u8809.codesamples.individual.study.bootandaws.service.members;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;

public interface MemberService {

    Long saveMember(Member saved);

    Member getMember(Long memberSn);
}
