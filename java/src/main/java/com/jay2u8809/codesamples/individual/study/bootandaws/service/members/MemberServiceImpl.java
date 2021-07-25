package com.jay2u8809.codesamples.individual.study.bootandaws.service.members;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.MemberRepository;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto.MemberJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findMemberBySn(Long memberSn) {
        return memberRepository
                .findById(memberSn)
                .orElse(null);
    }

    @Override
    public Member findMemberById(String memberId) {
        return memberRepository.findMemberById(memberId);
    }

    @Override
    public Long saveMember(MemberJoinRequestDto joinRequestDto) {
        return memberRepository
                .save(joinRequestDto.saveMember())
                .getMemberSn();
    }
}
