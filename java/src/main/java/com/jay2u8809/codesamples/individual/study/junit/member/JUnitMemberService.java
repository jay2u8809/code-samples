package com.jay2u8809.codesamples.individual.study.junit.member;

import java.util.List;
import java.util.Optional;

import com.jay2u8809.codesamples.common.code.MemberStatus;
import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitMember;
import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitStudy;

public interface JUnitMemberService {
  
    default void validate(String memberId) throws InvalidJUnitMemberException {
    }

    default JUnitMember findById(String memberId) throws JUnitMemberNotFoundException {
        return new JUnitMember();
    }

    default Optional<JUnitMember> findByMemberId(String memberId) {
        return Optional.of(new JUnitMember());
    }

    void notify(JUnitStudy newStudy);

    void saveNotifyHistory(JUnitStudy history);

    // ==== Mock Stubbing Method ====
    int countDormancyMember(MemberStatus status);

    boolean isExist(String memberId);

    String getMemberSnById(String memberId);

    Optional<JUnitMember> findByEmail(String email);

    JUnitMember findById(String memberId, MemberStatus status);

    void checkStatus(String memberId) throws InvalidJUnitMemberException;

    List<JUnitMember> fetchNormalMembers();
}
