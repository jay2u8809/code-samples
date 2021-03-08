package com.jay2u8809.codesamples.corp.uzjp.adakr.controller.naverapi;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.adakr.api.naverapi.map.NaverMapApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/corp/uzjp/adakr/naverapi/map")
public class NaverMapController extends CommonExtends {

    private static final String BASE_PATH = CORP_UZJP_HOME_PATH + "/adakr/naverapi/map/";

    // Naver GeoCoding Api ClientId : Issued after registration on NaverCloud Platform
    @Value("${open-api.naver.map.geocoding.client-id}")
    private String geoCodingClientId;

    private final NaverMapApi naverMapApi;

    @GetMapping("/entry/")
    public String moveGenerateNaverMapView (Model model) {

        // Form Url Data
        String formUrl = BASE_PATH + "generate/";
        model.addAttribute("formUrl", formUrl);

        // Naver Maps API Client ID
        model.addAttribute("naverMapsClientId", geoCodingClientId);

        return BASE_PATH + "naver_map";
    }

    /**
     *
     * @param address
     * @return
     */
    @PostMapping(value = "/generate/")
    @ResponseBody
    public ResponseEntity<?> generateNaverMap (@RequestParam String address) {

        logger.debug("Input Address : {}", address);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> mapInfo = naverMapApi.getGeoCodeInfo(address, false);
        result.put("mapInfo", mapInfo.get("addresses"));

        // Return Type : JSON
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
