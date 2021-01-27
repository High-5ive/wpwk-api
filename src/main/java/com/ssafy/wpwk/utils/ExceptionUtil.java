package com.ssafy.wpwk.utils;

import org.springframework.security.core.Authentication;

public class ExceptionUtil {

    public static boolean isInValidAuthentication(Authentication authentication) {
        return authentication == null ? true : false;
    }
}
