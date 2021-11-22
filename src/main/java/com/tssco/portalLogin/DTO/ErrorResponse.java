package com.tssco.portalLogin.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ErrorResponse<T> {
    // public static final Integer OK = 0;
    // public static final Integer ERROR = 100;
    private Boolean status = false;
    private String code;
    private String message;
    private Object response = null;
    // private String url;
    // private T data;
}
