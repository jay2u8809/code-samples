package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.MemberRepository;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto.MemberJoinRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Component
public class MemberMutationResolver implements GraphQLMutationResolver {

    private final MemberRepository memberRepository;

    /**
     * Save One Member Data
     * GraphQL Schema Mutation :
     * @param memberDTO
     * @return Member
     */
    public Member createMember(MemberJoinRequestDto memberDTO) {
        return this.memberRepository.save(memberDTO.saveMember());
    }

    /**
     * Delete One Member Data
     * GraphQL Schema Mutation :
     * @param memberSn
     * @return boolean
     */
    public boolean deleteMemberBySn(final Long memberSn) {
        // TODO Delete Member Process
        return true;
    }
}
