package cobas.coding.lol_a_z_backend.service;

import cobas.coding.lol_a_z_backend.controller.api.service.RiotApiService;
import cobas.coding.lol_a_z_backend.model.Champion;
import cobas.coding.lol_a_z_backend.model.SummonerStatsDTO;
import cobas.coding.lol_a_z_backend.repository.ChampionRepo;
import cobas.coding.lol_a_z_backend.repository.RepositoryProvider;
import cobas.coding.lol_a_z_backend.security.exception.SummonerNotExistException;
import cobas.coding.lol_a_z_backend.security.model.Summoner;
import cobas.coding.lol_a_z_backend.security.repo.SummonerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @Slf4j public class ChampionService {
	ChampionRepo repo;
	RiotApiService riotApiService;
	SummonerRepo userRepo;
	InitialChampionService initialChampionService;

	public ChampionService(ChampionRepo repo, RiotApiService riotApiService, InitialChampionService initialChampionService, RepositoryProvider repositoryProvider) {
		this.repo = repo;
		this.riotApiService = riotApiService;
		this.userRepo = repositoryProvider.getSummonerRepo();
		this.initialChampionService = initialChampionService;
	}

	private Summoner getSummoner() {
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Optional<Summoner> summoner = userRepo.findByUsernameEquals(username);
		if (summoner.isPresent()) {
			return summoner.get();
		} else {
			throw new SummonerNotExistException("Could not find User " + username);
		}

	}

	public List<Champion> getDefaultChamps() {
		return initialChampionService.getDefaultChampions();
	}

	public List<Champion> getAllChampions() {
		Summoner summoner = getSummoner();
		return summoner.getChampions().stream().sorted(Champion.Comparators.NAME).collect(Collectors.toList());
	}

	public void editChampion(Champion champion) {
		Summoner summoner = getSummoner();
		int champIndex = summoner.getChampions().indexOf(champion);
		summoner.getChampions().set(champIndex, champion);
		userRepo.save(summoner);
	}

	public List<Champion> getChampionsFilteredByAttribute(boolean filteredBy) {
		Summoner summoner = getSummoner();
		return summoner.getChampions().stream().filter(e -> e.isPlayed() == filteredBy).sorted(Champion.Comparators.NAME).collect(Collectors.toList());
	}

	public Champion getRandomChampionIsNotPlayed() {
		Summoner summoner = getSummoner();
		List<Champion> champions = summoner.getChampions().stream().filter(e -> !e.isPlayed()).collect(Collectors.toList());
		Collections.shuffle(champions);
		return champions.get(0);
	}

	public List<Champion> resetAllChampions() {
		Summoner summoner = getSummoner();
		List<Champion> champs = summoner.getChampions();
		for (Champion champ : champs) {
			champ.setPlayed(false);
			champ.setLoose(0);
			champ.setWins(0);
		}
		summoner.setChampions(champs);
		userRepo.save(summoner);
		return summoner.getChampions();
	}

	public List<SummonerStatsDTO> getChampionStats() {
		List<Summoner> summoners = userRepo.findAll();
		List<SummonerStatsDTO> statistics = new ArrayList<>();
		for (Summoner summoner : summoners) {
			List<Champion> summonerChamps = summoner.getChampions();
			for (Champion champ : summonerChamps) {
				statistics.add(SummonerStatsDTO.builder().championName(champ.getName()).summonerName(summoner.getUsername()).played(champ.isPlayed()).wins(champ.getWins())
						.looses(champ.getLoose()).build());
			}
		}
		return statistics;
	}

	public List<Champion> updateChampions() {
		Summoner summoner = getSummoner();
		List<Champion> newChamps = initialChampionService.getDefaultChampions();
		List<Champion> oldChamps = summoner.getChampions();
		List<Champion> champsToAdd = new ArrayList<>();
		for (Champion newChamp : newChamps) {
			if (!oldChamps.contains(newChamp)) {
				champsToAdd.add(newChamp);
			}
		}
		oldChamps.addAll(champsToAdd);
		userRepo.save(summoner);
		return summoner.getChampions();
	}
}
