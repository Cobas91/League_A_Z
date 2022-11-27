package cobas.coding.lol_a_z_backend.security.controller;

import cobas.coding.lol_a_z_backend.model.SummonerDTO;
import cobas.coding.lol_a_z_backend.security.model.PasswordResetDTO;
import cobas.coding.lol_a_z_backend.security.model.Summoner;
import cobas.coding.lol_a_z_backend.security.service.JWTUtilService;
import cobas.coding.lol_a_z_backend.security.service.SummonerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtilService jwtService;
    private final SummonerDetailService userService;

    @Autowired public LoginController(AuthenticationManager authenticationManager, JWTUtilService jwtService, SummonerDetailService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login") public String login(@RequestBody SummonerDTO summoner) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(summoner.getUsername(), summoner.getPassword()));
        return jwtService.createToken(new HashMap<>(), summoner.getUsername());
    }

    @PostMapping("/register") public String register(@RequestBody SummonerDTO summoner) {
        return jwtService.createToken(new HashMap<>(), userService.registerUser(summoner).getUsername());
    }
    @PostMapping("/password/reset")
    public String resetPassword(@RequestBody PasswordResetDTO passwordResetDTO){
        return userService.resetPassword(passwordResetDTO);
    }
}
