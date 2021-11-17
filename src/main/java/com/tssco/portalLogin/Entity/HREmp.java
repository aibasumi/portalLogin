package com.tssco.portalLogin.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "HREmp")
public class HREmp {
    @Id
    @Column(name ="EMP_ID")
    private String empId;

    @Column(name ="CH_NM")
    private String empName;

    @Column(name ="DEPT_CD_ACT")
    private String empDeptId;

    @Column(name ="DEPT_NM_ACT")
    private String empDeptName;
}
