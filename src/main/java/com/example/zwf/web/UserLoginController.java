package com.example.zwf.web;


import com.example.zwf.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserLoginController {
    @Autowired
    UserService userService;

    /**
     * 用户登录，返回用户身份
     * @param email
     * @param password
     * @param response
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping("/login")
    public String login (String email, String password, HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        String login=userService.login(email, password);
        System.out.println("登陆");
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        System.out.println("用户身份："+login);
        return login;
    }

}
