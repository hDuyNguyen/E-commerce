package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<Users> getUserProfile(@RequestHeader("Authorization")String jwt) throws UserException {

        Users users = userService.findUserProfileBtJwt(jwt);

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }
}
