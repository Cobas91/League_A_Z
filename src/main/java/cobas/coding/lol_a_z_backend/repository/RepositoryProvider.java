package cobas.coding.lol_a_z_backend.repository;

import cobas.coding.lol_a_z_backend.security.repo.SummonerRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class RepositoryProvider {
	@Autowired
	ChampionRepo championRepo;
	@Autowired
	SystemInformationRepository systemInformationRepository;
	@Autowired
	SummonerRepo summonerRepo;
}
