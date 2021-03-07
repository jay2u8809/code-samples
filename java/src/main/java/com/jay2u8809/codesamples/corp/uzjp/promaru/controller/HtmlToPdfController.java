package com.jay2u8809.codesamples.corp.uzjp.promaru.controller;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/corp/uzjp/promaru/html2pdf")
public class HtmlToPdfController extends CommonExtends {

    private static final String BASE_PATH = CORP_UZJP_HOME_PATH + "/promaru/html2pdf/";

    @GetMapping("/entry/")
    public String movePage() {

        return BASE_PATH + "html2pdf";
    }

}
