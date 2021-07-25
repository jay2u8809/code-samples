package com.jay2u8809.codesamples.individual.study.bootandaws.domain.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m ORDER BY m.memberId DESC")
    Member findMemberById(String memberId);
}
