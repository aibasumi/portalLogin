package com.tssco.portalLogin.DTO;

import lombok.Data;

@Data
public class ApiResponse {

    public static final Boolean FAIL = false;
    public static final Boolean SUCCESS = true; 

    private boolean status = true;
    private String code = "200";
    private Object response;
    private String message = "";

}
