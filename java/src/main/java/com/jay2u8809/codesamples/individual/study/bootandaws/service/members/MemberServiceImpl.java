package com.jay2u8809.codesamples.individual.study.bootandaws.service.members;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.MemberRepository;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto.MemberJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findMemberBySn(final Long memberSn) {
        return this.memberRepository
                .findById(memberSn)
                .orElse(null);
    }

    @Override
    public Member findMemberById(final String memberId) {
        return this.memberRepository.findMemberById(memberId);
    }

    @Override
    public List<Member> findAllMembers() {
        Sort descSort = Sort.by(Sort.Direction.DESC, "memberSn");
        return this.memberRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "memberSn"));
    }

    @Override
    public Member saveMember(MemberJoinRequestDto joinRequestDto) {
        return this.memberRepository
                .save(joinRequestDto.saveMember());
    }

    @Override
    public boolean deleteMember(final Long memberSn) {
        Member member = this.memberRepository.findById(memberSn).orElse(null);

        if (member == null)    return false;

        this.memberRepository.deleteById(memberSn);
        return true;
    }
}
