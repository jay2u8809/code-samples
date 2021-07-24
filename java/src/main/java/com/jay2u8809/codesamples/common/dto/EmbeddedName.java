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
public class EmbeddedName implements Serializable {

    @Column(name = "name_1", length = 100)
    private String givenName;

    @Column(name = "name_2", length = 100)
    private String surName;

    @Column(name = "name_3", length = 100)
    private String givenNameEn;

    @Column(name = "name_4", length = 100)
    private String surNameEn;

    public EmbeddedName() {
    }

    public EmbeddedName(String givenName, String surName) {
        this.setGivenName(givenName);
        this.setSurName(surName);
    }

    public EmbeddedName (String givenName, String surName, String givenNameEn, String surNameEn) {
        this.setGivenName(givenName);
        this.setSurName(surName);
        this.setGivenNameEn(givenNameEn);
        this.setSurNameEn(surNameEn);
    }

    public EmbeddedName(final EmbeddedName embeddedName) {
        if (embeddedName != null) {
            this.setGivenName(embeddedName.getGivenName());
            this.setSurName(embeddedName.getSurName());
            this.setGivenNameEn(embeddedName.getGivenNameEn());
            this.setSurNameEn(embeddedName.getSurNameEn());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmbeddedName)) {
            return false;
        }
        EmbeddedName that = (EmbeddedName) obj;
        return Objects.equals(this.givenName, that.givenName) &&
                Objects.equals(this.surName, that.surName) &&
                Objects.equals(this.givenNameEn, that.givenNameEn) &&
                Objects.equals(this.surNameEn, that.surNameEn);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.givenName != null && !this.givenName.isEmpty()) {
            sb.append(this.givenName).append(" ");
        }
        if (this.surName != null && !this.surName.isEmpty()) {
            sb.append(this.surName).append(" ");
        }
        if (this.givenNameEn != null && !this.givenNameEn.isEmpty()) {
            sb.append(this.givenNameEn).append(" ");
        }
        if (this.surNameEn != null && !this.surNameEn.isEmpty()) {
            sb.append(this.surNameEn).append(" ");
        }
        return sb.toString().trim();
    }
}
