package com.tssco.portalLogin.Config;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class AdServiceConfig {
    private String login_ldapurl = "ldap://172.24.19.22:389";
    private String login_ldapdc = "TSSCO";
}
