package com.tssco.portalLogin.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PTSys {

    @Id
    @Column(name ="sysType")
    private String sysType;

    @Column(name ="sysValue")
    private String sysValue;

}
