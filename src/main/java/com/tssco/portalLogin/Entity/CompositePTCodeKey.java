package com.tssco.portalLogin.Entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class CompositePTCodeKey implements Serializable {

    private String codeType;
    private String codeId;

}