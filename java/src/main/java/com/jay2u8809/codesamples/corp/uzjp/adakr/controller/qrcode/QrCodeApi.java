package com.jay2u8809.codesamples.corp.uzjp.adakr.controller.qrcode;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.adakr.api.qrcode.QrCodeService;
import com.jay2u8809.codesamples.corp.uzjp.adakr.controller.qrcode.dto.QrCodeViewEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/corp/uzjp/adakr/qrcode")
public class QrCodeApi extends CommonExtends {

    private static final String BASE_PATH = CORP_UZJP_HOME_PATH + "/adakr/qrcode/";

    private final QrCodeService qrCodeService;

    @GetMapping(value = "/entry/")
    public String moveGenerateQrCodeView (Model model) {

        // Form Url Data
        String formUrl = BASE_PATH + "generate/";
        model.addAttribute("formUrl", formUrl);

        return BASE_PATH + "generate_qrcode";
    }

    /**
     *
     * @param qrCodeViewEntry
     * @return
     */
    @PostMapping(value = "/generate/")
    @ResponseBody
    public ResponseEntity<?> generateQrCode (@RequestBody QrCodeViewEntry qrCodeViewEntry) {

        logger.debug("Input Uri : {}", qrCodeViewEntry.getQrcodeUri());

        Map<String, Object> result = new HashMap<>();
        String qrCode = qrCodeService.generateQrCodeByImageUri(qrCodeViewEntry.getQrcodeUri(), qrCodeViewEntry.getQrcodeSize());
//        String qrCode = "QRCODE TEST URI";
        result.put("qrCodeUrl", qrCode);

        // Return Type : JSON
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
