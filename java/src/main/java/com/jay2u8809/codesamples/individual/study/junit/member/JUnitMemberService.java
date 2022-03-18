package com.jay2u8809.codesamples.individual.study.junit.member;

import java.util.Optional;

import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitMember;

public interface JUnitMemberService {
  
    default void validate(Long memberId) throws InvalidJUnitMemberException {
    }
    default JUnitMember findById(Long memberId) throws JUnitMemberNotFoundException {
        return new JUnitMember();
    }

    default Optional<JUnitMember> findByMemberId(Long memberId) {
        return Optional.of(new JUnitMember());
    }
}
