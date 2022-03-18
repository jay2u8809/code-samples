package com.jay2u8809.codesamples.individual.study.junit.study;

import java.util.Optional;

import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitMember;
import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitStudy;
import com.jay2u8809.codesamples.individual.study.junit.member.JUnitMemberService;

public class JUnitStudyService {

    private final JUnitMemberService memberService;

    private final JUnitStudyRepository repository;

    public JUnitStudyService(JUnitMemberService memberService, JUnitStudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public JUnitStudy createNewStudy(Long memberId, JUnitStudy study) {
        Optional<JUnitMember> member = memberService.findByMemberId(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: " + memberId)));
        return repository.save(study);
    }
}
