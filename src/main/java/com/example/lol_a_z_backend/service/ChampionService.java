package com.example.lol_a_z_backend.service;

import com.example.lol_a_z_backend.controller.api.service.RiotApiService;
import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.model.SummonerStatsDTO;
import com.example.lol_a_z_backend.repository.ChampionRepo;
import com.example.lol_a_z_backend.security.exception.SummonerNotExistException;
import com.example.lol_a_z_backend.security.model.Summoner;
import com.example.lol_a_z_backend.security.repo.SummonerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service @Slf4j public class ChampionService {
	ChampionRepo repo;
	RiotApiService riotApiService;
	SummonerRepo userRepo;
	InitialChampionService initialChampionService;

	public ChampionService(ChampionRepo repo, RiotApiService riotApiService, SummonerRepo userRepo, InitialChampionService initialChampionService) {
		this.repo = repo;
		this.riotApiService = riotApiService;
		this.userRepo = userRepo;
		this.initialChampionService = initialChampionService;
	}

	public List<Champion> getAllChampsForStatistic(){
		return repo.findAll();
	}

	private Summoner getSummoner() {
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Optional<Summoner> summoner = userRepo.findByUsername(username);
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
		List<Champion> champs = repo.findAllBySummonerId(summoner.getId());
		champs.sort(Champion.Comparators.NAME);
		return champs;
	}

	public void editChampion(Champion champion) {
		Summoner summoner = getSummoner();
		List<Champion> userChamps = summoner.getChampions();
		champion.setSummoner(summoner);
		userChamps.set(userChamps.indexOf(champion), champion);
		userRepo.save(summoner);
	}

	//TODO Methode in Repo auslagern und via Repositoy funktionalität umsetzen
	public List<Champion> getChampionsFilteredByAttribute(boolean filteredBy) {
		return repo.findAllfindAllByPlayedAndSummonerIdOrderByNameAsc(filteredBy, getSummoner().getId());
	}

	//TODO Methode in Repo auslagern und via Repositoy funktionalität umsetzen
	public Champion getRandomChampionIsNotPlayed() {
		List<Champion> champsPlayable = repo.findAllfindAllByPlayedAndSummonerIdOrderByNameAsc(false, getSummoner().getId());
		Collections.shuffle(champsPlayable);
		return champsPlayable.get(0);
	}

	public List<Champion> resetAllChampions() {
		List<Champion> allChamps = repo.findAllBySummonerId(getSummoner().getId());
		List<Champion> resetedChamps = new ArrayList<>();
		for (Champion champ : allChamps) {
			champ.setPlayed(false);
			champ.setLoose(0);
			champ.setWins(0);
			resetedChamps.add(champ);
		}
		return repo.saveAll(resetedChamps);
	}

	public void saveListOfChamps(List<Champion> champs) {
		repo.saveAll(champs);
	}

	public List<SummonerStatsDTO> getChampionStats(){
		List<Summoner> summoners = userRepo.findAll();
		List<SummonerStatsDTO> statistics = new ArrayList<>();
		for(Summoner summoner : summoners ){
			List<Champion> summonerChamps = repo.findAllBySummonerId(summoner.getId());
			for(Champion champ: summonerChamps){
				statistics.add(SummonerStatsDTO.builder().championName(champ.getName()).summonerName(summoner.getUsername()).played(champ.isPlayed()).wins(champ.getWins()).looses(champ.getLoose()).build());
			}
		}
		return statistics;
	}

}
