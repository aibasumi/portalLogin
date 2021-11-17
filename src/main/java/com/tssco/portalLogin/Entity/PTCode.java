package com.tssco.portalLogin.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="PTCode")
@IdClass(CompositePTCodeKey.class)
public class PTCode {

    @Id
    @Column(name ="codeType")
    private String codeType;

    @Id
    @Column(name ="codeId")
    private String codeId;

    @Column(name ="codeDesc")
    private String codeDesc;

}