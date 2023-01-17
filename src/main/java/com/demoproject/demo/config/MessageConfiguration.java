package com.demoproject.demo.config;

import net.rakugakibox.util.YamlResourceBundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Configuration
public class MessageConfiguration implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {
        // LocaleChangeInterceptor로 Locale 정보가 변경되면 SessionLocaleResolver에 의해서 해당 세션의 Locale 정보가 변경되는 것
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREAN);    // 세션에 지역 설정
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {  // spring에서 국제화 처리를 위해 제공하는 인터셉터
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");   // "lang"이름을 갖는 쿼리 파라미터의 값을 바탕으로 언어정보를 변경한다.
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()); // 사전에 설정한 인터셉터를 시스템 레지스트리에 추가해준다.
    }

    @Bean
    public MessageSource messageSource( // yml파일에서 참조한다.
            @Value("${spring.messages.basename}") String basename,
            @Value("${spring.messages.encoding}") String encoding) {
        YamlMessageSource ms = new YamlMessageSource();
        ms.setBasename(basename);
        ms.setDefaultEncoding(encoding);
        ms.setAlwaysUseMessageFormat(true);
        ms.setUseCodeAsDefaultMessage(true);
        ms.setFallbackToSystemLocale(true);
        return ms;
    }

    private static class YamlMessageSource extends ResourceBundleMessageSource {
        @Override   // locale 정보에 따라 맞는 yml 파일을 basename과 locale 조합으로 찾아서 읽는다.
        protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
            return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
        }
    }
}

