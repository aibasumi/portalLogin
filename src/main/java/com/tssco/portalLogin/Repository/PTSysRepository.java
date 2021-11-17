package com.tssco.portalLogin.Repository;

import com.tssco.portalLogin.Entity.PTSys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PTSysRepository extends JpaRepository<PTSys,String>{

    @Query(value ="SELECT sysValue FROM PTSys WHERE sysType = 'LoginType'",nativeQuery = true)
    String queryLoginType();
    
}
