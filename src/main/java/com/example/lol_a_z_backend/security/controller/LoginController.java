package com.example.lol_a_z_backend.security.controller;

import com.example.lol_a_z_backend.security.model.AppUserDTO;
import com.example.lol_a_z_backend.security.service.AppUserDetailService;
import com.example.lol_a_z_backend.security.service.JWTUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtilService jwtService;
    private final AppUserDetailService userService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JWTUtilService jwtService, AppUserDetailService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUserDTO appUser){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
        return jwtService.createToken(new HashMap<>(), appUser.getUsername());
    }

    @PostMapping("/register")
    public String register(@RequestBody AppUserDTO appUser){
        return jwtService.createToken(new HashMap<>(), userService.registerUser(appUser).getUsername());
    }
}
