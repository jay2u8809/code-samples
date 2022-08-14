package com.jay2u8809.codesamples.corp.uzjp.adakr.service.qrcode;

import com.jay2u8809.codesamples.common.CommonConst;
import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.qrcode.config.QrCodeConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = QrCodeConfig.class)
public class QrCodeServiceTest extends CommonExtends {

    @Autowired
    private QrCodeConfig config;

    @DisplayName("기능 개발 전 로직 설계 코드의 테스트")
    @Test
    public void generateQrCodeByImageUriLogicTest () {

        // Dummy Uri Data
        String sampleUri = "https://www.google.com";

        Assertions.assertAll(
            // case1: normal, expected: pass
                () -> Assertions.assertNotNull(this.generateQrCodeByImageUriLogic(sampleUri, 10)),

            // case2: size is null or zero, expected: pass
                () -> Assertions.assertNotNull(this.generateQrCodeByImageUriLogic(sampleUri, null)),
                () -> Assertions.assertNotNull(this.generateQrCodeByImageUriLogic(sampleUri, 0)),

            // case3: size < 0, expected: pass
                () -> Assertions.assertNotNull(this.generateQrCodeByImageUriLogic(sampleUri, -1)),

            // case4: 1000 < size, expected: pass
                () -> Assertions.assertNotNull(this.generateQrCodeByImageUriLogic(sampleUri, 1001)),

            // case5: uri is null, expected: fail, exception
                () -> Assertions.assertThrows(AssertionFailedError.class, () -> this.generateQrCodeByImageUriLogic(null, 10)),

            // case6: uri size is zero, expected: fail, exception
                () -> Assertions.assertThrows(AssertionFailedError.class, () -> this.generateQrCodeByImageUriLogic("", 10)),

            // case7: uri regex
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> this.generateQrCodeByImageUriLogic(String.valueOf(312321), 10)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> this.generateQrCodeByImageUriLogic("QRCODE", 10))
        );

    }

    /**
     * QRCODE generation
     * 기능 개발 전 로직 설계를 위한 코드
     * @param inputUri  URi information to generate QRCODE
     * @param inputSize QRCODE image size setting
     * @return String
     */
    private String generateQrCodeByImageUriLogic (String inputUri, Integer inputSize) {

        // 1) input uri is null: throw exception
        Assertions.assertNotNull(inputUri, () -> "URI cannot be null, " + inputUri);
        Assertions.assertTrue(inputUri.length() > 0, () -> "URI cannot be null, " + inputUri);

        // 1-2) uri regex
        boolean isMatches = Pattern.matches(CommonConst.REGEX_URL, inputUri);
        if (!isMatches) {
            throw new IllegalArgumentException("URI cannot be uri, " + inputUri);
        }

        // 2) input size is null: replace default value, min-max value
        int size = (inputSize == null || inputSize == 0)
                ? 100
                : (inputSize < 0 || 1000 < inputSize)
                    ? 100
                    : inputSize;
        String sizeQuery = size + "x" + size;

        // 3) build uri: use uri component
        String apiUri = this.config.getApiUri();
        URI uri = UriComponentsBuilder
                .fromUriString(apiUri)
                .path("/")
                .queryParam("size", sizeQuery)
                .queryParam("data", inputUri)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
        logger.info("Generate Qr Code Request URL: {}", uri);

        // 4) request: use restTemplate
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            logger.info("Generate Qr Code Result: {}", result);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @DisplayName("기능 개발 후 테스트")
    @Test
    public void generateQrCode() {
        QrCodeService qrCodeService = new QrCodeService(this.config);

        String sampleUri = "http://www.google.com";
        Assertions.assertAll(
                // case1: normal, expected: pass
                () -> Assertions.assertNotNull(qrCodeService.generateQrCodeByImageUri(sampleUri, 10)),

                // case2: size is null or zero, expected: pass
                () -> Assertions.assertNotNull(qrCodeService.generateQrCodeByImageUri(sampleUri, null)),
                () -> Assertions.assertNotNull(qrCodeService.generateQrCodeByImageUri(sampleUri, 0)),

                // case3: size < 0, expected: pass
                () -> Assertions.assertNotNull(qrCodeService.generateQrCodeByImageUri(sampleUri, -1)),

                // case4: 1000 < size, expected: pass
                () -> Assertions.assertNotNull(qrCodeService.generateQrCodeByImageUri(sampleUri, 1001)),

                // case5: uri is null, expected: fail, exception
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> qrCodeService.generateQrCodeByImageUri(null, 10)),

                // case6: uri size is zero, expected: fail, exception
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> qrCodeService.generateQrCodeByImageUri("", 10)),

                // case7: uri regex
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> qrCodeService.generateQrCodeByImageUri(String.valueOf(312321), 10)),
                () -> Assertions.assertThrows(IllegalArgumentException.class, () -> qrCodeService.generateQrCodeByImageUri("QRCODE", 10))
        );
    }
}