package com.tssco.portalLogin.Exception;

import javax.servlet.http.HttpServletRequest;

import com.tssco.portalLogin.DTO.ErrorResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormatExption.class)
    @ResponseBody
    public ErrorResponse<String> FormatExptionHandler(HttpServletRequest request, FormatExption exception) throws Exception {
        String code = this.getCode(exception.getMessage());
        return handleErrorInfo(request, code,exception.getMessage(), exception);
    }

    @ExceptionHandler(SecurityExption.class)
    @ResponseBody
    public ErrorResponse<String> SecurityExption(HttpServletRequest request, SecurityExption exception) throws Exception {
        return handleErrorInfo(request, exception.code,exception.message, exception);
    }

    private ErrorResponse<String> handleErrorInfo(HttpServletRequest request,String code, String message, Exception exception) {

        ErrorResponse<String> errorMessage = new ErrorResponse<>();
        errorMessage.setMessage(message);
        errorMessage.setCode(code);
        // errorMessage.setUrl(request.getRequestURL().toString());
        return errorMessage;
    }


    private String getCode(String message){
        if(message.matches("(.*)must not be null(.*)")){
            return "format.ColumnIsNull";
        }
        return "fail";
    }
    
}
