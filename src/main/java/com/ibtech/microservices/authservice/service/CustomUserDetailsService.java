package com.ibtech.microservices.authservice.service;

import com.ibtech.microservices.authservice.data.User;
import com.ibtech.microservices.authservice.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String input) {
        Optional<User> user = null;


        user = userRepository.findByUsername(input);

        if (!user.isPresent()) {
            throw new BadCredentialsException("Bad credentials");
        }

        new AccountStatusUserDetailsChecker().check(user.get());

        return user.get();
    }

}
