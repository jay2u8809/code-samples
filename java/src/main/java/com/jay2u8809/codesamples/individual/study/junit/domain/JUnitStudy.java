package com.jay2u8809.codesamples.individual.study.junit.domain;

import com.jay2u8809.codesamples.common.code.MemberStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JUnitStudy {

    private MemberStatus status;

    private int limit;

    private JUnitMember owner;

    public JUnitStudy() {
        this.status = MemberStatus.Normal;
    }

    public JUnitStudy(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit >= 0");
        }
        this.limit = limit;
    }
}
