package com.welend.welend.service;

import com.welend.welend.DAO.UserDAO;
import com.welend.welend.model.User;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@CommonsLog
public class UserService {

    private UserDAO userDAO;
    public void login() {
        log.info("enter login from user service...");
        User user = userDAO.getUserByIdAndEmail(BigInteger.valueOf(3), "mina@cashcall.com");
    }
}
