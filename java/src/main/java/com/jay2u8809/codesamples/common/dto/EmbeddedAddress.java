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
public class EmbeddedAddress implements Serializable {

    @Column(name = "country_code", length = 30)
    private String countryCode;

    @Column(name = "zip_code", length = 30)
    private String zipCode;

    @Column(name = "address_1", length = 200)
    private String address1;

    @Column(name = "address_2", length = 200)
    private String address2;

    @Column(name = "address_3", length = 200)
    private String address3;

    @Column(name = "address_4", length = 200)
    private String address4;

    public EmbeddedAddress() {
    }

    public EmbeddedAddress(String countryCode, String zipCode, String address1, String address2, String address3, String address4) {

        this.setCountryCode(countryCode);
        this.setZipCode(zipCode);
        this.setAddress1(address1);
        this.setAddress2(address2);
        this.setAddress3(address3);
        this.setAddress4(address4);
    }

    public EmbeddedAddress(final EmbeddedAddress embeddedAddress) {
        if (embeddedAddress != null) {
            this.setCountryCode(embeddedAddress.getCountryCode());
            this.setZipCode(embeddedAddress.getZipCode());
            this.setAddress1(embeddedAddress.getAddress1());
            this.setAddress2(embeddedAddress.getAddress2());
            this.setAddress3(embeddedAddress.getAddress3());
            this.setAddress4(embeddedAddress.getAddress4());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmbeddedAddress)) {
            return false;
        }
        EmbeddedAddress that = (EmbeddedAddress) obj;
        return Objects.equals(this.countryCode, that.countryCode) &&
                Objects.equals(this.zipCode, that.zipCode) &&
                Objects.equals(this.address1, that.address1) &&
                Objects.equals(this.address2, that.address2) &&
                Objects.equals(this.address3, that.address3) &&
                Objects.equals(this.address4, that.address4);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.countryCode != null && !this.countryCode.isEmpty()) {
            sb.append(this.countryCode).append(" ");
        }
        if (this.zipCode != null && !this.zipCode.isEmpty()) {
            sb.append(this.zipCode).append(" ");
        }
        if (this.address1 != null && !this.address1.isEmpty()) {
            sb.append(this.address1).append(" ");
        }
        if (this.address2 != null && !this.address2.isEmpty()) {
            sb.append(this.address2).append(" ");
        }
        if (this.address3 != null && !this.address3.isEmpty()) {
            sb.append(this.address3).append(" ");
        }
        if (this.address4 != null && !this.address4.isEmpty()) {
            sb.append(this.address4).append(" ");
        }
        return sb.toString().trim();
    }
}
