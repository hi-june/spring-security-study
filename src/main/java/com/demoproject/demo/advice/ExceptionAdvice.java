package com.demoproject.demo.advice;

import com.demoproject.demo.advice.exception.UserNotFoundCException;
import com.demoproject.demo.model.response.CommonResult;
import com.demoproject.demo.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 2. @RestControllerAdvice가 등록되어있는 ExceptionAdvice에 해당 에러를 처리하기 위한 Handler를 만들어준다.
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   // Http Response Code를 500으로 설정해준다.
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult
                (Integer.parseInt(getMessage("unKnown.code")), getMessage("unKnown.msg"));  // exception-locale.yml에 있는 내용 참고
    }

    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /***
     * 유저를 찾지 못했을 때 발생시키는 예외
     */
    @ExceptionHandler(UserNotFoundCException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult
                (Integer.parseInt(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
    }
}
