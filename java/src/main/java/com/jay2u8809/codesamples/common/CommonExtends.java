package com.jay2u8809.codesamples.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CommonExtends {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static final String CORP_UZJP_HOME_PATH = "/corp/uzjp";

    /**
     * Get the session ID
     * @param request
     * @return session ID
     */
    public static HttpSession getHttpSession(HttpServletRequest request) {

        HttpSession httpSession = request.getSession(false);
        if (httpSession == null) {
            httpSession = request.getSession(true);
        }

        return httpSession;
    }

    /**
     * Get Error Messages
     *
     * @param bindingResult
     * @param result
     */
    protected void bindErrorResult(BindingResult bindingResult, Map<String, Object> result) {
        for (FieldError error : bindingResult.getFieldErrors()) {
            result.put(error.getField(), (error.getCode() != null && !error.getCode().isEmpty() ? error.getCode() : error.getDefaultMessage()));
        }
    }
}
