package com.emobilnost.configuration;

import com.emobilnost.configuration.EmobilityUserPrincipal;
import com.emobilnost.model.Users;
import com.emobilnost.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class EmobilnostUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
        Users user = usersRepository.findFirstByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
 
        return new EmobilityUserPrincipal(user);
    }



}
