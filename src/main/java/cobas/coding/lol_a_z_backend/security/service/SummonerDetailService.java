package cobas.coding.lol_a_z_backend.security.service;

import cobas.coding.lol_a_z_backend.model.Champion;
import cobas.coding.lol_a_z_backend.security.exception.UserExistsException;
import cobas.coding.lol_a_z_backend.security.model.Summoner;
import cobas.coding.lol_a_z_backend.security.repo.SummonerRepo;
import cobas.coding.lol_a_z_backend.service.ChampionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
        return summonerRepo.findByUsernameEquals(username).map(summoner -> User.withUsername(username).password(summoner.getPassword()).authorities("user").build())
                .orElseThrow(() -> new UsernameNotFoundException("Username does not exist: " + username));
    }

    public Summoner registerUser(Summoner summoner) {
        if (summonerExist(summoner)) {
            throw new UserExistsException("This User already exists");
        }
        try {
            summoner.setPassword(new BCryptPasswordEncoder().encode(summoner.getPassword()));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        List<Champion> champs = championService.getDefaultChamps();
        summoner.setChampions(champs);
        summonerRepo.save(summoner);
        return summoner;
    }

    private boolean summonerExist(Summoner summoner) {

        return summonerRepo.findByUsernameEquals(summoner.getUsername()).isPresent();

    }
}
