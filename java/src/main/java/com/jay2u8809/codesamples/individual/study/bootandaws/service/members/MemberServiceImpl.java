package com.jay2u8809.codesamples.individual.study.bootandaws.service.members;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Long saveMember(Member saved) {
        return memberRepository
                .save(saved)
                .getMemberSn();
    }

    @Override
    public Member getMember(Long memberSn) {
        return memberRepository
                .findById(memberSn)
                .orElseThrow(() -> new EntityNotFoundException("Member is Not Founded, MemberSn : " + memberSn));
    }
}
