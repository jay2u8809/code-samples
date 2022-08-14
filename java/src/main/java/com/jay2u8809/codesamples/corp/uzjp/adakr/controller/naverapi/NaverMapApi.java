package com.jay2u8809.codesamples.corp.uzjp.adakr.controller.naverapi;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map.NaverMapService;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map.dto.GeoCodingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/corp/uzjp/adakr/naverapi/map")
public class NaverMapApi extends CommonExtends {

    private final NaverMapService naverMapService;

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
        GeoCodingResponseDto mapInfo = naverMapService.getGeoCodeInfo(address);
        result.put("mapInfo", mapInfo.getAddresses());

        // Return Type : JSON
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
