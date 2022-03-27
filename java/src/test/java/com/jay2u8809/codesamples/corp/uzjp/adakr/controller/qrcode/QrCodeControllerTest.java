package com.jay2u8809.codesamples.corp.uzjp.adakr.controller.qrcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@RunWith(SpringRunner.class)
@WebMvcTest(controllers = QrCodeApi.class)
public class QrCodeControllerTest {
/*   https://theheydaze.tistory.com/218   */
/*
“No tests found for given includes” when running Gradle tests in IntelliJ IDEA
File > Setting (Ctrl+Alt+S)
Build, Execution, Deployment > Build Tools > gradle
Run Tests using: Intellij IDEA
 */
    private final String path = "/corp/uzjp/adakr/qrcode";

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void moveGenerateQrCodeViewTest() throws Exception {
//
//        mockMvc.perform(get(path + "/entry/"))
//                .andExpect(status().isOk());
//
//    }

//    @Test
//    public void makeQrCodePath() throws Exception {
//
//        mockMvc.perform(post("/qrcode/generate/")
//                        .param("qrcodeUri", "https://www.naver.com"))
//                .andExpect(status().isOk());
//    }
}