package com.jay2u8809.codesamples.corp.uzjp.adakr.api.qrcode;

import com.jay2u8809.codesamples.common.CommonConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Service
public class QrCodeApi {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // API URI to generate QRCODE
    @Value("${open-api.qrcode.api-uri}")
    private String qrCodeApiUri;

    // Basic QRCODE image size
    private int qrCodeSize = 100;

    /**
     * QRCODE generation
     * @param inputUri  URi information to generate QRCODE
     * @param inputSize QRCODE image size setting
     * @return String
     */
    public String generateQrCodeByImageUri (String inputUri, Integer inputSize) {

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

        // URI when API URI is null
        String tempQrCodeApiUri = "https://api.qrserver.com/v1/create-qr-code/";
        // check QRCODE API URI
        qrCodeApiUri = (qrCodeApiUri == null || qrCodeApiUri.isEmpty())
                    ? tempQrCodeApiUri
                    : qrCodeApiUri;
        logger.debug("QR CODE API ULR : {}", qrCodeApiUri);

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
