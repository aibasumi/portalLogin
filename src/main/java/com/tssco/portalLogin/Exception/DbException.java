package com.tssco.portalLogin.Exception;

import lombok.Getter;
import lombok.Setter;

public class DbException extends Exception {
    @Getter
    @Setter
    protected String message;
    public DbException() {
        setMessage("Session is not found!");
    }
    public DbException(String message) {
        this.message = message;
    }
}
