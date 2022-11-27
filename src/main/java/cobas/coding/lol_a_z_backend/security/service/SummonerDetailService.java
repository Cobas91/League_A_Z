package cobas.coding.lol_a_z_backend.security.service;

import cobas.coding.lol_a_z_backend.model.Champion;
import cobas.coding.lol_a_z_backend.model.SummonerDTO;
import cobas.coding.lol_a_z_backend.security.exception.UserExistsException;
import cobas.coding.lol_a_z_backend.security.model.PasswordResetDTO;
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

    public Summoner registerUser(SummonerDTO summonerDTO) {
        if (summonerExist(summonerDTO.getUsername())) {
            throw new UserExistsException("This User already exists");
        }
        List<Champion> champs = championService.getDefaultChamps();
        Summoner summoner = Summoner.builder()
                .username(summonerDTO.getUsername())
                .password(new BCryptPasswordEncoder().encode(summonerDTO.getPassword()))
                .email(summonerDTO.getEmail())
                .champions(champs)
                .build();
        summonerRepo.save(summoner);
        return summoner;
    }

    private boolean summonerExist(String username) {
        return summonerRepo.findByUsernameEquals(username).isPresent();

    }

    public String resetPassword(PasswordResetDTO passwordResetDTO) {
        //TODO
        log.info(passwordResetDTO.toString());
        return "";
    }
}
