package com.jay2u8809.codesamples.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class EmbeddedTel implements Serializable {

    @Column(name = "phone_no", length = 30)
    private String phoneNo;

    public EmbeddedTel() {
    }

    public EmbeddedTel(String phoneNo) {
        this.setPhoneNo(phoneNo);
    }

    public EmbeddedTel(final EmbeddedTel embeddedTel) {
        if (embeddedTel != null) {
            this.setPhoneNo(embeddedTel.getPhoneNo());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmbeddedTel)) {
            return false;
        }
        EmbeddedTel that = (EmbeddedTel) obj;
        return Objects.equals(this.phoneNo, that.phoneNo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.phoneNo != null && !this.phoneNo.isEmpty()) {
            sb.append(this.phoneNo);
        }
        return sb.toString().trim();
    }
}
