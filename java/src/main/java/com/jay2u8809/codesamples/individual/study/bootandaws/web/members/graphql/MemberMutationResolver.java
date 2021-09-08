package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.MemberRepository;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto.MemberJoinRequestDto;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto.MemberJoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional(rollbackFor = Exception.class)
public class MemberMutationResolver extends CommonExtends implements GraphQLMutationResolver {

    private final MemberRepository memberRepository;

    /**
     * Save One Member Data
     * GraphQL Schema Mutation : createMember(saveMemberInfo: SaveMember!): Member!
     * @param memberDTO
     * @return Member
     */
    public MemberJoinResponseDto createMember(MemberJoinRequestDto memberDTO) {
        return new MemberJoinResponseDto(this.memberRepository.save(memberDTO.saveMember()));
    }

    /**
     * Delete One Member Data
     * GraphQL Schema Mutation : deleteMemberBySn(memberSn: Int!): Boolean!
     * @param memberSn
     * @return boolean
     */
    public Boolean deleteMemberBySn(final long memberSn) {
        if (memberSn <= 0)  return false;
        logger.debug(" === Mutation Delete Member's memberSn: {} === ", memberSn);
        // TODO Delete Member Process
        return true;
    }
}
