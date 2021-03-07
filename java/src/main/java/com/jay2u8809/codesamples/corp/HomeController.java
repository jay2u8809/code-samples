package com.jay2u8809.codesamples.corp;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("corp.HomeController")
public class HomeController extends CommonExtends {

    @GetMapping("/corp/")
    public String corpHome(HttpServletRequest request, Model model) {

        return "corp/" + "index";
    }
}
