package com.jay2u8809.codesamples.individual.study.bootandaws.web.members.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.members.Member;
import com.jay2u8809.codesamples.individual.study.bootandaws.service.members.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberQueryResolver implements GraphQLQueryResolver {

    private final MemberService memberService;

    /**
     * Get One Member Data
     * GraphQL Schema Query : member(memberSn: Int!): Member!
     * @param memberSn
     * @return
     */
    public Member getMember(final Long memberSn) {
        return this.memberService.findMemberBySn(memberSn);
    }
}
