package com.tssco.portalLogin;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class DecryptInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String pwd = applicationContext.getEnvironment().getProperty("spring.datasource.password").toString() ;
        System.err.println("解密前>>>>>>>>>>>  " + pwd);
        byte[] bytes = null ;
        String nPwd = "" ;
        try {
            bytes = Base64.getDecoder().decode(pwd);
            nPwd = new String(bytes,"UTF-8") ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("BASE64解密：" + nPwd);
        Properties properties = new Properties();
        properties.put("spring.datasource.password",nPwd) ;
        PropertiesPropertySource pwdPropertySource = new PropertiesPropertySource(this.getClass().getName(),properties) ;
        // 這裏要使用addFirst（我們是覆蓋而不是新增，如果採用addLast則密碼不會被修改）
        applicationContext.getEnvironment().getPropertySources().addFirst(pwdPropertySource);
        pwd = applicationContext.getEnvironment().getProperty("spring.datasource.password").toString() ;
        System.err.println("解密後>>>>>>>>>>>  " + pwd);    
    }   
}
