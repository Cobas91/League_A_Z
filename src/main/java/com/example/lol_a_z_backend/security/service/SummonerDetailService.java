package com.example.lol_a_z_backend.security.service;

import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.security.model.Summoner;
import com.example.lol_a_z_backend.security.repo.SummonerRepo;
import com.example.lol_a_z_backend.service.ChampionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j public class SummonerDetailService implements UserDetailsService {
    private final SummonerRepo summonerRepo;
    private final ChampionService championService;

    public SummonerDetailService(SummonerRepo summonerRepo, ChampionService championService) {
        this.summonerRepo = summonerRepo;
        this.championService = championService;
        List<Summoner> allUsers = summonerRepo.findAll();
        if (allUsers.isEmpty()) {
            summonerRepo.save(Summoner.builder().username("Admin").password(new BCryptPasswordEncoder().encode("Admin")).build());
        }
    }

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return summonerRepo.findByUsername(username).map(summoner -> User.withUsername(username).password(summoner.getPassword()).authorities("user").build())
                .orElseThrow(() -> new UsernameNotFoundException("Username does not exist: " + username));
    }

    public Summoner registerUser(Summoner summoner) {
        try {
            summoner.setPassword(new BCryptPasswordEncoder().encode(summoner.getPassword()));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        List<Champion> champs = championService.getDefaultChamps();
        Summoner newSummoner = summonerRepo.save(summoner);
        for (Champion emptyChamp : champs) {
            emptyChamp.setSummoner(newSummoner);
        }
        championService.saveListOfChamps(champs);
        return newSummoner;
    }
}
