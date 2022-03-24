package com.jay2u8809.codesamples.individual.study.junit.domain;

import com.jay2u8809.codesamples.common.code.MemberStatus;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JUnitStudy {

    private MemberStatus status;

    private String title;

    private int limit;

    private JUnitMember owner;

    private boolean isOpen;

    private LocalDateTime openedDateTime;

    public JUnitStudy() {
        this.status = MemberStatus.Normal;
    }

    public JUnitStudy(int limit, String title) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit >= 0");
        }
        this.limit = limit;
        this.title = (title == null || title.length() == 0) ? "Untitled" : title;
    }

    public void open() {
        if (this.isOpen) {
            return;
        }
        this.setOpen(true);
        this.openedDateTime = LocalDateTime.now();
    }
}
