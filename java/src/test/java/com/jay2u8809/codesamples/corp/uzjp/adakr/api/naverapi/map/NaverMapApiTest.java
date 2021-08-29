package com.jay2u8809.codesamples.corp.uzjp.adakr.api.naverapi.map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay2u8809.codesamples.common.CommonControllerTest;
import com.jay2u8809.codesamples.corp.uzjp.adakr.api.naverapi.map.dto.NaverGeoCodingResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NaverMapApiTest extends CommonControllerTest {

    // Sample Data
    private final String sampleResultJson = "{\n" +
            "    \"status\": \"OK\",\n" +
            "    \"meta\": {\n" +
            "        \"totalCount\": 1,\n" +
            "        \"page\": 1,\n" +
            "        \"count\": 1\n" +
            "    },\n" +
            "    \"addresses\": [\n" +
            "        {\n" +
            "            \"roadAddress\": \"경기도 성남시 분당구 불정로 6 그린팩토리\",\n" +
            "            \"jibunAddress\": \"경기도 성남시 분당구 정자동 178-1 그린팩토리\",\n" +
            "            \"englishAddress\": \"6, Buljeong-ro, Bundang-gu, Seongnam-si, Gyeonggi-do, Republic of Korea\",\n" +
            "            \"addressElements\": [\n" +
            "                {\n" +
            "                    \"types\": [\n" +
            "                        \"POSTAL_CODE\"\n" +
            "                    ],\n" +
            "                    \"longName\": \"13561\",\n" +
            "                    \"shortName\": \"\",\n" +
            "                    \"code\": \"\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"x\": \"127.10522081658463\",\n" +
            "            \"y\": \"37.35951219616309\",\n" +
            "            \"distance\": 20.925857741585514\n" +
            "        }\n" +
            "    ],\n" +
            "    \"errorMessage\": \"\"\n" +
            "}";

    /** yml setting data **/
    // Naver GeoCoding Api Url
    @Value("${open-api.naver.map.geocoding.api-url}")
    private String geoCodingApiUlr;

    // Naver GeoCoding Api ClientId : Issued after registration on NaverCloud Platform
    @Value("${open-api.naver.map.geocoding.client-id}")
    private String geoCodingClientId;

    // Naver GeoCoding Api ClientSecret : Issued after registration on NaverCloud Platform
    @Value("${open-api.naver.map.geocoding.client-secret}")
    private String geoCodingClientSecret;

    @BeforeEach
    public void setNaverMapApiInfo() {
        this.geoCodingApiUlr = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode";
        this.geoCodingClientId = "ID_INFO";
        this.geoCodingClientSecret = "SECRET_INFO";
    }

    @Test
    public void naverMapsApiTest() {

        // argument : true -> http test / false -> sample data Test
        this.makeGeoCoderTest(false);
    }

    private void makeGeoCoderTest(boolean isHttpConnection) {

        // Address data
        String address = "서울 강남구 대치동 942-5번지 삼성빌딩 12층";
        // Convert to utf-8 encoding
        try {
            address = URLEncoder.encode(address, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Fail to Encoding",e);
        }

        // Reference docs : https://apidocs.ncloud.com/ko/ai-naver/maps_geocoding/geocode/
        String apiURL = geoCodingApiUlr + "?query=" + address;
        String clientId = geoCodingClientId;
        String clientSecret = geoCodingClientSecret;

        // JSON -> MAP
        Map<String, Object> resultMap = null;
        // JSON -> NaverGeoCodingDto
        NaverGeoCodingResultDto naverGeoCodingResultDto = null;

        try {

            StringBuilder result = new StringBuilder();

            if (isHttpConnection) {
                URL url = new URL(apiURL);
                HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
                http.setRequestProperty("Content-Type", "application/json");
                http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
                http.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
                http.setRequestMethod("GET");
                http.connect();

                InputStreamReader in = new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(in);

                // receive Api result(Address geocoding Info)
                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line).append("\n");
                }

                br.close();
                in.close();
                http.disconnect();
            }

            // Address geocoding Info Conversion : Json -> JAVA Object
            ObjectMapper mapper = new ObjectMapper();
            String resultAddressJson = isHttpConnection ? result.toString() : this.sampleResultJson;

            // convert JSON to Map
            resultMap = mapper.readValue(resultAddressJson, new TypeReference<>(){});
            // convert JSON to NaverApiGeoCodingDto
            naverGeoCodingResultDto = mapper.readValue(resultAddressJson, NaverGeoCodingResultDto.class);

        } catch (JsonGenerationException e) {
            logger.error("Json Generation Exception : {}", e.getMessage());
        } catch (JsonMappingException e) {
            logger.error("Json Mapping Exception : {}", e.getMessage());
        } catch(IOException e) {
            logger.error("I/O Exception : {}", e.getMessage());
        }

        assertNotNull(resultMap);
        assertNotNull(naverGeoCodingResultDto);

        // value : status
        logger.info("STATUS_INFO : {}, {}", resultMap.get("status").toString(), naverGeoCodingResultDto.getStatus());

        // values :totalCount, page, count
        logger.info("META_INFO : {}, {}", resultMap.get("meta").toString(), naverGeoCodingResultDto.getMetaInfo().toString());

        // values : roadAddress, jibunAddress, englishAddress, addressElements, x(lng), y(lat), distance
        // There is only one (NAVER specification)
        logger.info("ADDRESS_INFO : {}, {}", resultMap.get("addresses").toString(), naverGeoCodingResultDto.getAddresses().get(0).toString());

        // value : [error contents]
        logger.info("ERROR_MSG : {}, {}", resultMap.get("errorMessage").toString(), naverGeoCodingResultDto.getErrorMessage());
    }
}