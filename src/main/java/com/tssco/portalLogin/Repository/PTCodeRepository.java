package com.tssco.portalLogin.Repository;

import java.util.List;

import com.tssco.portalLogin.Entity.PTCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PTCodeRepository extends JpaRepository<PTCode,String>{

    @Query(value ="SELECT * FROM PTCode WHERE codeType = 'TSSTitle'",nativeQuery = true)
    List <PTCode> queryJobLevel();

    @Query(value ="SELECT * FROM PTCode WHERE codeType = 'BlockKind'",nativeQuery = true)
    List <PTCode> queryBlockType();
}