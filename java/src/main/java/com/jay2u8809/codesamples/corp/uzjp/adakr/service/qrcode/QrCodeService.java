package com.jay2u8809.codesamples.corp.uzjp.adakr.service.qrcode;

import com.jay2u8809.codesamples.common.CommonConst;
import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.qrcode.config.QrCodeConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
     * @param uri  URi information to generate QRCODE
     * @param size QRCODE image size setting
     * @return String
     */
    public String generateQrCodeByImageUri (String uri, Integer size) {

        logger.debug("INPUT URI : {} , INPUT SIZE : {}", uri, size);

        // 1-1) check input uri
        if (ObjectUtils.isEmpty(uri)) {
            throw new IllegalArgumentException("URI cannot be null, " + uri);
        }
        // 1-2) check uri regex
        boolean isMatches = Pattern.matches(CommonConst.REGEX_URL, uri);
        if (!isMatches) {
            throw new IllegalArgumentException("URI cannot be uri, " + uri);
        }

        // 2) check input size
        int qrCodeSize = (size == null || size == 0)
                ? this.defaultSize
                : (size < this.minSize || this.maxSize < size)
                    ? this.defaultSize
                    : size;
        String sizeQuery = qrCodeSize + "x" + qrCodeSize;

        // 3) build uri
        URI qrCodeUri = UriComponentsBuilder
                .fromUriString(this.qrCodeConfig.getApiUri())
                .path("/")
                .queryParam("size", sizeQuery)
                .queryParam("data", uri)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
        logger.info("Generate Qr Code Request URL: {}", qrCodeUri);

        // 4) request
        String result = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            result = restTemplate.getForObject(uri, String.class);
            logger.debug("Generate Qr Code Result: {}", result);
        } catch (RestClientException e) {
            logger.error("ERROR: Qr code generation failed, {}", e.getMessage());
        }

        return result;
    }

}
