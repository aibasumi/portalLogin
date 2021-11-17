package com.tssco.portalLogin.DTO;

import lombok.Data;

@Data
public class DbOperateResponse {

    private Boolean status = true;
    private String code = "success";
    private String message = "";
    
}
