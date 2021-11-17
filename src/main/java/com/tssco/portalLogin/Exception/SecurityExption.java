package com.tssco.portalLogin.Exception;

import lombok.Getter;
import lombok.Setter;

public class SecurityExption extends Exception {

    @Getter
    @Setter
    protected String code;
    protected String message;

    public SecurityExption(String code,String message) {
        this.code = code;
        this.message = message;
    }
    
}
