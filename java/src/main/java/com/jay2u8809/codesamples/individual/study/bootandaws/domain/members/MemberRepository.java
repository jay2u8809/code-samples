package com.jay2u8809.codesamples.individual.study.bootandaws.domain.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // https://goodgid.github.io/Spring-Data-JPA-Query_Part_2/
    // TODO Not Working Query
    @Query("SELECT m FROM #{#entityName} m WHERE m.memberId like %?1% ORDER BY m.memberSn asc")
    Member findMemberById(String memberId);

    @Query ("SELECT m FROM #{#entityName} m WHERE m.memberId like %?1% and m.memberSn > 0 ORDER BY m.memberSn DESC")
    List<Member> findMembersById(String memberId);
}
