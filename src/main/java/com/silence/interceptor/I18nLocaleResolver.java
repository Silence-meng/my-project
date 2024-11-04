package com.silence.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * @author silence
 * @since 2024/10/9 11:12
 **/
@Slf4j
public class I18nLocaleResolver implements LocaleResolver {

    @NotNull
    @Override
    public Locale resolveLocale(@NotNull HttpServletRequest request) {
        String language = request.getHeader("language");
        log.info("language: {}", language);
        if (StringUtils.isNotBlank(language)) {
            String[] languageArray = language.split("_");
            if (languageArray.length == 2) {
                return new Locale(languageArray[0], languageArray[1]);
            } else if (languageArray.length == 1) {
                return new Locale(languageArray[0]);
            }
        }
        log.info("language is null, use default locale: {}", Locale.getDefault());
        return Locale.getDefault();
    }

    @Override
    public void setLocale(@NotNull HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
