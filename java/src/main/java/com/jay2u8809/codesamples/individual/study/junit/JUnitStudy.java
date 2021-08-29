package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.common.code.MemberStatus;

public class JUnitStudy {

    private MemberStatus status;

    private int limit;

    public JUnitStudy(int limit) {
        this.limit = limit;
    }

    public MemberStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }
}
