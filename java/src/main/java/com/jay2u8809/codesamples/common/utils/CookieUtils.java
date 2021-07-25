package com.jay2u8809.codesamples.common.utils;

import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class CookieUtils extends CookieGenerator {

    // Cookie name
    public static final String COOKIE_NAME = "DEFCOOKIE";

    // Set the maximum duration (in seconds)
    private static final Integer COOKIE_MAX_AGE = 24 * 60 * 60 * 3; // 3 Days


    public CookieUtils() {
        super.setCookieName(COOKIE_NAME);
        super.setCookieMaxAge(COOKIE_MAX_AGE);
    }


    /**
     * save cookie data
     * @param clazz
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param <T>
     */
    public static <T extends CookieGenerator> void addCookie(Class<T> clazz, HttpServletResponse response,
                                                             String cookieName,
                                                             String cookieValue) {
        try {
//            T cookieGenerator = clazz.newInstance();
            T cookieGenerator = clazz.getDeclaredConstructor().newInstance();
            cookieGenerator.setCookieName(cookieName);
            cookieGenerator.addCookie(response, cookieValue);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * get cookie data
     * @param cookieName
     * @param request:HttpServletResponse
     * @return
     */
    public static String getCookieValue(String cookieName, HttpServletRequest request) {

        Optional<String> result = Optional.empty();

        if (request != null) {
            try {
                Cookie[] cookies = request.getCookies();
                result = Arrays.stream(cookies)
                        .filter(cookie -> Objects.equals(cookie.getName(), cookieName))
                        .map(Cookie::getValue)
                        .findFirst();
            } catch (NullPointerException e) {
                return null;
            }
        }

        return result.orElse(null);
    }


    /**
     * remove cookie data
     * @param clazz:CookieGenerator class
     * @param response:HttpServletResponse
     * @param cookieName
     * @return void
     */
    public static <T extends CookieGenerator> void removeCookie(Class<T> clazz, HttpServletResponse response,
                                                                String cookieName){
        try {
//            T cookieGenerator = clazz.newInstance();
            T cookieGenerator = clazz.getDeclaredConstructor().newInstance();
            cookieGenerator.setCookieName(cookieName);
            cookieGenerator.removeCookie(response);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
