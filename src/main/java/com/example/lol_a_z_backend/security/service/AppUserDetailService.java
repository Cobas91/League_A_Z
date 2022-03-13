package com.example.lol_a_z_backend.security.service;

import com.example.lol_a_z_backend.security.model.AppUserDTO;
import com.example.lol_a_z_backend.security.repo.AppUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppUserDetailService implements UserDetailsService {
    private final AppUserRepo appUserRepo;

    public AppUserDetailService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
        List<AppUserDTO> allUsers = appUserRepo.findAll();
        if(allUsers.isEmpty()){
            appUserRepo.save(AppUserDTO.builder().username("Admin").password(new BCryptPasswordEncoder().encode("Admin")).build());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(username)
                .map(appUser -> User
                .withUsername(username)
                .password(appUser.getPassword())
                .authorities("user")
                .build())
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist: "+username));
    }

    public AppUserDTO registerUser(AppUserDTO appUser) {
        try{
            appUser.setPassword(new BCryptPasswordEncoder().encode(appUser.getPassword()));
        }catch (IllegalArgumentException e){
            log.error(e.getMessage(), e);
        }
        return appUserRepo.save(appUser);
    }
}
