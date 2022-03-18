package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.common.code.MemberStatus;

public class JUnitStudy {

    private MemberStatus status;

    private int limit;

    public JUnitStudy() {
        this.status = MemberStatus.Normal;
    }

    public JUnitStudy(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit >= 0");
        }
        this.limit = limit;
    }

    public MemberStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }
}
