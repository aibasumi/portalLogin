package com.tssco.portalLogin.Exception;

import lombok.Getter;
import lombok.Setter;

public class FormatExption extends Exception {

    @Getter
    @Setter
    protected String message;
    // public FormatExption() {
    //     setMessage("");
    // }
    public FormatExption(String message) {
        this.message = message;
    }

    // protected String code;
    // public FormatExption() {
    //     setCode("");
    // }
    
}
