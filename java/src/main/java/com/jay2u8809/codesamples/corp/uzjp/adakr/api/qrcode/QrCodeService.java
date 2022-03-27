package com.jay2u8809.codesamples.corp.uzjp.adakr.api.qrcode;

import com.jay2u8809.codesamples.common.CommonConst;
import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.adakr.api.qrcode.config.QrCodeConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class QrCodeService extends CommonExtends {

    // API URI to generate QRCODE
//    @Value("${open-api.qrcode.api-uri}")
//    private String qrCodeApiUri;

    private final QrCodeConfig qrCodeConfig;

    // Basic QRCODE image size
    protected final int defaultSize = 100;
    protected final int minSize = 1000;
    protected final int maxSize = 1000;

    /**
     * QRCODE generation
     * @param inputUri  URi information to generate QRCODE
     * @param inputSize QRCODE image size setting
     * @return String
     */
    public String generateQrCodeByImageUri (String inputUri, Integer inputSize) {

        logger.debug("INPUT URI : {} , INPUT SIZE : {}", inputUri, inputSize);

        // 1) input uri is null: throw exception
        if (inputUri == null || inputUri.length() == 0) {
            throw new IllegalArgumentException("URI cannot be null, " + inputUri);
        }
        // 1-2) uri regex
        boolean isMatches = Pattern.matches(CommonConst.REGEX_URL, inputUri);
        if (!isMatches) {
            throw new IllegalArgumentException("URI cannot be uri, " + inputUri);
        }

        // 2) input size is null: replace default value, min-max value
        int size = (inputSize == null || inputSize == 0)
                ? this.defaultSize
                : (inputSize < this.minSize || this.maxSize < inputSize)
                    ? this.defaultSize
                    : inputSize;
        String sizeQuery = size + "x" + size;

        // 3) build uri: use uri component
        URI uri = UriComponentsBuilder
                .fromUriString(this.qrCodeConfig.getApiUri())
                .path("/")
                .queryParam("size", sizeQuery)
                .queryParam("data", inputUri)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
        logger.info("Generate Qr Code Request URL: {}", uri);

        // 4) request: use restTemplate
        String result = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            result =restTemplate.getForObject(uri, String.class);
            logger.debug("Generate Qr Code Result: {}", result);
        } catch (RestClientException e) {
            logger.error("ERROR: Qr code generation failed, {}", e.getMessage());
        }

        return result;
    }
}
