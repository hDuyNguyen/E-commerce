package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Override
    public Users findUserById(Long userId) throws UserException {

        Optional<Users> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }
         throw new UserException("User not found with id: " +userId);
    }

    @Override
    public Users findUserProfileBtJwt(String jwt) throws UserException {

        String email = jwtProvider.getEmailFromToken(jwt);

        Users users = userRepository.findByEmail(email);

        if (users == null) {
            throw new UserException("User not found with email " + email);
        }

        return users;
    }
}
