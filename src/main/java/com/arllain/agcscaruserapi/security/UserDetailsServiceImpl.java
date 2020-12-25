package com.arllain.agcscaruserapi.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arllain.agcscaruserapi.domain.User;
import com.arllain.agcscaruserapi.repository.UserRepository;

/**
 * @author arllain
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        final User user = userRepository.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("login '" + login + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(login)//
                .password(user.getPassword())//
                .authorities(new ArrayList<>())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
