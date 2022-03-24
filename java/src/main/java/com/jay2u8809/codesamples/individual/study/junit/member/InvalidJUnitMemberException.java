package com.jay2u8809.codesamples.individual.study.junit.member;

public class InvalidJUnitMemberException extends RuntimeException {
    public InvalidJUnitMemberException() {
        super();
    }

    public InvalidJUnitMemberException(String message) {
        super(message);
    }

    public InvalidJUnitMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidJUnitMemberException(Throwable cause) {
        super(cause);
    }
}
