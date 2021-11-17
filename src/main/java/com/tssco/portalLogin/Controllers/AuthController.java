package com.tssco.portalLogin.Controllers;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tssco.portalLogin.DTO.ApiResponse;
import com.tssco.portalLogin.Exception.SecurityExption;
import com.tssco.portalLogin.Repository.PTSysRepository;
import com.tssco.portalLogin.Services.LoginService;
import com.tssco.portalLogin.Utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;

@ConfigurationProperties(prefix = "jwt")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PTSysRepository ptSysRepository;

    @ApiOperation(value="登入", notes="登入")
    @RequestMapping( value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse login( @RequestBody String postdata) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeRoot = objectMapper.readTree(postdata);
        String account = jsonNodeRoot.get("account").textValue();
        String password = jsonNodeRoot.get("password").textValue();

        String loginType = ptSysRepository.queryLoginType();

        //員編登入
        if(loginType.equals("1") ){
            Map<String, Object> empResult = loginService.empLogin(account, password);
            //登入失敗
            if(empResult.get("status").toString() == "false") {
                throw new SecurityExption("sec.IsNotAuth",empResult.get("message").toString());
            }

        }else{ //AD登入
            Map<String, Object> loginResult = loginService.adLogin(account,password);
            //登入失敗
            if(loginResult.get("status").toString() == "false") {
                throw new SecurityExption("sec.IsNotAuth",loginResult.get("message").toString());
            }
        }

        //AD帳號正確，產生jwt token
        String token = jwtUtils.generateToken(account);

        Map<String, Object> result = new HashMap<>();
        result.put( "token",token); 
        result.put( "account",account);

        ApiResponse  responseData = new ApiResponse();
        responseData.setStatus(true);
        responseData.setCode("200");
        responseData.setResponse(result);
        return responseData;
    }

    @RequestMapping( value = "/vaildToken", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse vaildToken(@RequestBody String postdata) throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNodeRoot = objectMapper.readTree(postdata);
        String token = jsonNodeRoot.get("token").textValue();

        ApiResponse  responseData = new ApiResponse();

        try {
            Claims claims = jwtUtils.validateToken(token);
            responseData.setResponse(claims);
        } catch (Exception e) {
            throw new SecurityExption("sec.SingVerifyFailed",e.getMessage());
        }

        return responseData; 
    }
    
}
