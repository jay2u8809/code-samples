package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import com.jay2u8809.codesamples.individual.study.bootandaws.service.members.MemberService;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.members.dto.MemberJoinResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Transactional(readOnly = true)
public class MemberQueryResolver extends CommonExtends implements GraphQLQueryResolver {

    private final MemberService memberService;

    /**
     * Get One Member Data
     * GraphQL Schema Query : member(memberSn: Int!): Member!
     * @param memberSn
     * @return
     */
    public MemberJoinResponseDto getMember(final long memberSn) {
        if (memberSn <= 0)  return null;
        logger.debug(" === Query Member's memberSn: {} === ", memberSn);

        Member member = this.memberService.findMemberBySn(memberSn);
        if (ObjectUtils.isEmpty(member))    return null;
        logger.debug(" === Query Member's memberId: {} === ", member.getMemberId());

        return new MemberJoinResponseDto(member);
    }

    /**
     * Get All Members Data
     * GraphQL Schema Query : allMembers(count: Int, offset: Int): [Member]!
     * @param count
     * @param offset
     * @return
     */
    public List<MemberJoinResponseDto> getAllMembers(final int count, final int offset) {
        if (count <= 0 || offset <= 0)  return null;
        logger.debug(" === Query Member List Count: {}, Offset: {} === ", count, offset);

        return this.memberService.findAllMembers().stream()
                .map(MemberJoinResponseDto::new)
                .collect(Collectors.toList());
    }
}
