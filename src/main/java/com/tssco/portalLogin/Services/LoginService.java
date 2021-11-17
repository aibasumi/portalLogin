package com.tssco.portalLogin.Services;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.tssco.portalLogin.Config.AdServiceConfig;
import com.tssco.portalLogin.Entity.HREmp;
import com.tssco.portalLogin.Repository.HREmpRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdServiceConfig adServiceConfig;

    @Autowired
    private HREmpRepository hrEmpRepository;

    private static Logger logger = LogManager.getLogger(LoginService.class.getName());
    
    /** 
     * AD 登入
     * 
     * @param account
     * @param password
     * @return Map<String, Object>
     */
    public Map<String, Object> adLogin(String account, String password){

        Hashtable<String, String> env = new Hashtable<String,String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, adServiceConfig.getLogin_ldapurl());
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, adServiceConfig.getLogin_ldapdc() + "\\" + account);
        env.put(Context.SECURITY_CREDENTIALS, password);

        Boolean status = false;
        String message = "success";

        LdapContext ctx = null;
        try {
            ctx = new InitialLdapContext(env, null);
            status = true;
        } catch (javax.naming.AuthenticationException e) {
            message = account + ":登入失敗- [員工編號] 或 [AD密碼] 輸入錯誤 ! => " + e.getMessage();
            logger.warn(message);
        } catch (javax.naming.CommunicationException e) {
            message = account + ":登入失敗- 找入到認證主機 ! => " + e.getMessage();
            logger.error(message);
        } catch (Exception e) {
            message = account + ":登入失敗- 發生未知的錯誤，請洽系統管理員 ! => " + e.getMessage();
            logger.error(message);
        } finally {
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (NamingException e) {
                    message = account + ":ctx.close()發生錯誤 ! => " + e.getMessage();
                    logger.error(message);
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("status",status); 
        result.put("message",message);
        return result;
    }
    

    
    /** 
     * 員編登入
     * 
     * @param account
     * @param password
     * @return Map<String, Object>
     */
    public Map<String, Object> empLogin(String account, String password){
        Boolean status = true;
        String message = "success";

        List<HREmp> empResult = hrEmpRepository.findByEmpId(account);
        if(empResult.size() <= 0) {
            status = false;
            message = "Incorrect account or password";
            logger.warn(message);
        }

        if(account.equals(password) == false){
            status = false;
            message = "Incorrect account or password";
            logger.warn(message);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("status",status); 
        result.put("message",message);
        return result;
    }
}
