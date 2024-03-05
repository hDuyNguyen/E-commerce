package com.project.ecommerce.controller;

import com.project.ecommerce.config.JwtProvider;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.request.LoginRequest;
import com.project.ecommerce.response.AuthResponse;
import com.project.ecommerce.serivce.CartService;
import com.project.ecommerce.serivce.imp.CustomUserServiceImplementation;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomUserServiceImplementation customUserServiceImplementation;
    @Autowired
    CartService cartService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Users users) throws UserException {

        String email = users.getEmail();
        String password = users.getPassword();
        String firstName = users.getFirst_name();
        String lastName = users.getLast_name();

        Users isEmailExist = userRepository.findByEmail(email);

        if (isEmailExist != null) {
            throw new UserException("Email is already used with another account");
        }

        Users createdUser = new Users();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirst_name(firstName);
        createdUser.setLast_name(lastName);

        Users savedUser=userRepository.save(createdUser);
        Cart cart = cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signup Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signin Success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username,String password) {
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);

        if (userDetails==null) {
            throw new BadCredentialsException("Invalid Username");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException(("Invalid Password"));
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
    }
}
