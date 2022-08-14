package com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.config.NaverConfig;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map.config.MapConfigDto;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map.dto.GeoCodingResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = NaverConfig.class)
public class NaverMapServiceTest extends CommonExtends {

    private final String CLIENT_ID_HEADER_NAME = "X-NCP-APIGW-API-KEY-ID";
    private final String CLIENT_SECRET_HEADER_NAME = "X-NCP-APIGW-API-KEY";

    /** yml setting data **/
    // https://bottom-to-top.tistory.com/46
    // Naver GeoCoding Api Url
    @Value("${open-api.naver.map.geocoding.api-url}")
    private String geoCodingApiUlr;

    // Naver GeoCoding Api ClientId : Issued after registration on NaverCloud Platform
    @Value("${open-api.naver.map.geocoding.client-id}")
    private String geoCodingClientId;

    // Naver GeoCoding Api ClientSecret : Issued after registration on NaverCloud Platform
    @Value("${open-api.naver.map.geocoding.client-secret}")
    private String geoCodingClientSecret;

    @Autowired
    private NaverConfig naverConfig;

    @Test
    public void naverMapsApiTest() {
        // argument : true -> http test / false -> sample data Test
        GeoCodingResponseDto result = this.getGeoCodeInfoLogic("천호동");
        System.out.println(result.toString());
    }

    private void makeGeoCoderTest() {

        // Address data
        String address = "서울 강남구 대치동 942-5번지 삼성빌딩 12층";
        // Convert to utf-8 encoding
        try {
            address = URLEncoder.encode(address, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Fail to Encoding",e);
        }

        String apiURL = geoCodingApiUlr + "?query=" + address;
        String clientId = geoCodingClientId;
        String clientSecret = geoCodingClientSecret;

        // JSON -> MAP
        Map<String, Object> resultMap = null;
        // JSON -> NaverGeoCodingDto
        GeoCodingResponseDto geoCodingResponseDto = null;

        try {

            StringBuilder result = new StringBuilder();

            URL url = new URL(apiURL);
            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
            http.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            http.setRequestProperty(this.CLIENT_ID_HEADER_NAME, clientId);
            http.setRequestProperty(this.CLIENT_SECRET_HEADER_NAME, clientSecret);
            http.setRequestMethod(HttpMethod.GET.name());
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

            // Address geocoding Info Conversion : Json -> JAVA Object
            ObjectMapper mapper = new ObjectMapper();
            String resultAddressJson = result.toString();

            // convert JSON to Map
            resultMap = mapper.readValue(resultAddressJson, new TypeReference<>(){});
            // convert JSON to NaverApiGeoCodingDto
            geoCodingResponseDto = mapper.readValue(resultAddressJson, GeoCodingResponseDto.class);

        } catch (JsonGenerationException e) {
            logger.error("Json Generation Exception : {}", e.getMessage());
        } catch (JsonMappingException e) {
            logger.error("Json Mapping Exception : {}", e.getMessage());
        } catch(IOException e) {
            logger.error("I/O Exception : {}", e.getMessage());
        }

        assertNotNull(resultMap);
        assertNotNull(geoCodingResponseDto);

        // value : status
        logger.info("STATUS_INFO : {}, {}", resultMap.get("status").toString(), geoCodingResponseDto.getStatus());

        // values :totalCount, page, count
        logger.info("META_INFO : {}, {}", resultMap.get("meta").toString(), geoCodingResponseDto.getMeta().toString());

        // values : roadAddress, jibunAddress, englishAddress, addressElements, x(lng), y(lat), distance
        // There is only one (NAVER specification)
        logger.info("ADDRESS_INFO : {}, {}", resultMap.get("addresses").toString(), geoCodingResponseDto.getAddresses().get(0).toString());

        // value : [error contents]
        logger.info("ERROR_MSG : {}, {}", resultMap.get("errorMessage").toString(), geoCodingResponseDto.getErrorMessage());
    }

    /**
     * 기능 개발 전 로직 설계를 위한 코드
     * @param address
     * @return
     */
    private GeoCodingResponseDto getGeoCodeInfoLogic(String address) {

        // 1) check address
        Assertions.assertNotNull(address, () -> "Address cannot be null, " + address);
        Assertions.assertTrue(address.length() > 0, () -> "Address cannot be null, " + address);

        // 2) get naver config
        boolean isDummy = this.naverConfig.isDummy();
        MapConfigDto.GeoCoding geoCodingConfig = this.naverConfig.getMap().getGeoCoding();

        // 2-1) build header: https://e2e2e2.tistory.com/15
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(this.CLIENT_ID_HEADER_NAME, geoCodingConfig.getClientId());
        headers.add(this.CLIENT_SECRET_HEADER_NAME, geoCodingConfig.getClientSecret());
        // 2-2) build uri
        String apiUri = geoCodingConfig.getApiUrl();
        URI uri = UriComponentsBuilder
                .fromUriString(apiUri)
                .queryParam("address", address)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
        logger.info("Naver GeoCoding Request URL: {}", uri);

        // 3) request
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<GeoCodingResponseDto> result = isDummy ? this.getDummyResultDto() : restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), GeoCodingResponseDto.class);
            logger.info("Generate Qr Code Result: {}", result);
            return result.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @DisplayName("Get Map Config Data")
    @Test
    void get_map_config_data_test() {
        NaverMapService api = new NaverMapService(this.naverConfig);
        System.out.println(api.getMapConfig().getGeoCoding().getApiUrl());
    }

    /**
     * Address geocoding Info Conversion : Json -> JAVA Object
     */
    private ResponseEntity<GeoCodingResponseDto> getDummyResultDto() throws JsonProcessingException {
        String resultAddressJson = this.getDummyResultJson();
        // Status
        HttpStatus httpStatus = HttpStatus.OK;
        // Header: TODO
        // Body: JSON -> NaverGeoCodingDto
        ObjectMapper mapper = new ObjectMapper();
        GeoCodingResponseDto geoCodingResponseDto = mapper.readValue(resultAddressJson, GeoCodingResponseDto.class);
        // result
        ResponseEntity responseEntity = new ResponseEntity(geoCodingResponseDto, httpStatus);
        return responseEntity;
    }

    private String getDummyResultJson() {
        return "{\n" +
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
    }
}