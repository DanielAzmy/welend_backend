package com.welend.welend.controller;

import com.welend.welend.DTO.BaseResponse;
import com.welend.welend.DTO.UserLogin;
import com.welend.welend.model.User;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CommonsLog
@RequestMapping("/api")
public class PortalController {
    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody UserLogin request)  {
        try {
            log.info("Enter login function...");
            User user = new User();

            return new BaseResponse("success", "login success", request);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new RuntimeException();
        }
    }
}
