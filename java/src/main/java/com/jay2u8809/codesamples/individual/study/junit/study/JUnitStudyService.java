package com.jay2u8809.codesamples.individual.study.junit.study;

import java.util.Optional;

import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitMember;
import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitStudy;
import com.jay2u8809.codesamples.individual.study.junit.member.JUnitMemberService;

public class JUnitStudyService {

    private final JUnitMemberService memberService;

    private final JUnitStudyRepository studyRepository;

    public JUnitStudyService(JUnitMemberService memberService, JUnitStudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.studyRepository = repository;
    }

    public JUnitStudy createNewStudy(String memberId, JUnitStudy study) {
        Optional<JUnitMember> member = this.memberService.findByMemberId(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: " + memberId)));
        this.memberService.notify(study);
        this.memberService.saveNotifyHistory(study);
        return this.studyRepository.save(study);
    }

    public JUnitStudy openStudy(JUnitStudy study) {
        study.open();
        JUnitStudy openStudy = this.studyRepository.save(study);
        this.memberService.notify(openStudy);
        return openStudy;
    }
}
