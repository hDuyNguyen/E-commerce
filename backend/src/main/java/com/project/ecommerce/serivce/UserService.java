package com.project.ecommerce.serivce;

import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Users;
import org.springframework.stereotype.Service;

public interface UserService {

    public Users findUserById(Long userId) throws UserException;

    public Users findUserProfileBtJwt(String jwt) throws UserException;
}
