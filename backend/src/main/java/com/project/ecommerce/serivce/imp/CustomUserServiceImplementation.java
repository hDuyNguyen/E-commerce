package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.findByEmail(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found with email" + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(users.getEmail(), users.getPassword(), authorities);
    }
}
