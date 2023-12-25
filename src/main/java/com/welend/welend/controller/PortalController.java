package com.welend.welend.controller;

import com.welend.welend.DAO.UserDAO;
import com.welend.welend.DTO.BaseResponse;
import com.welend.welend.DTO.UserLogin;
import com.welend.welend.model.User;
import com.welend.welend.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@CommonsLog
@RequestMapping("/api")
@EnableJpaRepositories
public class PortalController {

    @Autowired
    private UserService userService;
    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody UserLogin request)  {
        try {
            log.info("Enter login function...");
            userService.login();
            return new BaseResponse("success", "login success", request);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new RuntimeException();
        }
    }
}
