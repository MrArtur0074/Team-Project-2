package com.sharpness.user_service.application.service;

import com.sharpness.user_service.domain.user.CustomUser;
import com.sharpness.user_service.infrastructure.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private CustomUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<CustomUser> user = repository.findByUsername(username);
        if(user.isPresent()){
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        }
        else{
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(CustomUser user){
        if (user.getProfile() == null || user.getProfile().getRole() == null) {
            return new String[]{"USER"}; // Роль по умолчанию
        }
        return user.getProfile().getRole().split(",");
    }


}
