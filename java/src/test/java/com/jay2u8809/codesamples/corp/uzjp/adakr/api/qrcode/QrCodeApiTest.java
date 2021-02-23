package com.jay2u8809.codesamples.corp.uzjp.adakr.api.qrcode;

import com.jay2u8809.codesamples.common.CommonConst;
import com.jay2u8809.codesamples.common.CommonControllerTest;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QrCodeApiTest extends CommonControllerTest {

    // API URI to generate QRCODE
    private final String qrCodeApiUri = "https://api.qrserver.com/v1/create-qr-code/";

    // Basic QRCODE image size
    private int qrCodeSize = 100;

    @Test
    public void generateQrCodeByImageUriTest () {

        // Dummy Uri Data
        List<String> dummyUriList = new ArrayList<>();
        dummyUriList.add("https://www.google.com");
        dummyUriList.add("https://www.msn.com");
        dummyUriList.add("https://www.instagram.com");

        dummyUriList = dummyUriList.stream()
                .map(f -> this.generateQrCodeByImageUri(f, null))
                .collect(Collectors.toList());

        // Print Result
        dummyUriList.forEach(System.out::println);
    }

    /**
     * QRCODE generation
     * @param inputUri  URi information to generate QRCODE
     * @param inputSize QRCODE image size setting
     * @return String
     */
    private String generateQrCodeByImageUri (String inputUri, Integer inputSize) {

        logger.info("INPUT URI : {} , INPUT SIZE : {}", inputUri, inputSize);

        String resultUri = null;

        if (inputUri == null || inputUri.length() == 0) {
            logger.debug("inputUri is Null");
            return resultUri;
        }

        // check URI Expression
        if (Pattern.compile(CommonConst.REGEX_URL).matcher(inputUri).matches()) {
            logger.info("inputUri is Not URI");
            return resultUri;
        }

        // UTF8 Encoding
        try {
            inputUri = URLEncoder.encode(inputUri, StandardCharsets.UTF_8.toString());
            logger.debug("ENCODING URI : {}", inputUri);
        } catch (UnsupportedEncodingException e) {
            logger.error("Fail to UTF-8 Encoding : {}", inputUri);
            return resultUri;
        }

        // QRCODE image size setting
        qrCodeSize = (inputSize == null || inputSize == 0) ? qrCodeSize : inputSize;
        String size = String.valueOf(qrCodeSize);
        // QRCODE image size used for URI
        String imageSize = new StringBuilder(size)
                            .append("x")
                            .append(size).toString();
        logger.info("QR CODE IMAGE SIZE : {}", imageSize);

        // generate QRCODE URI
        StringBuilder uriBuilder
                = new StringBuilder()
                .append(qrCodeApiUri)
                .append("?")
                .append("&size=").append(imageSize)
                .append("&data=").append(inputUri)
                ;

        resultUri = uriBuilder.toString();
        logger.info("RESULT QR CODE URI : {}", resultUri);

        return resultUri;
    }
}