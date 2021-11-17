package com.tssco.portalLogin.Repository;

import java.util.List;

import com.tssco.portalLogin.Entity.HREmp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HREmpRepository extends JpaRepository<HREmp,String>{


   List <HREmp> findByEmpId(String empId);
    
}
