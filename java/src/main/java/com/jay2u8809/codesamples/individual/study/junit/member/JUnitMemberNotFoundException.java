package com.jay2u8809.codesamples.individual.study.junit.member;

public class JUnitMemberNotFoundException extends RuntimeException {
    public JUnitMemberNotFoundException() {
        super();
    }

    public JUnitMemberNotFoundException(String message) {
        super(message);
    }

    public JUnitMemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public JUnitMemberNotFoundException(Throwable cause) {
        super(cause);
    }
}
