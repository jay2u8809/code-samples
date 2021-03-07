package com.jay2u8809.codesamples.corp.uzjp.adakr.controller.qrcode.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class QrCodeViewEntry implements Serializable {

    private String qrcodeUri;

    private Integer qrcodeSize = 0;
}
