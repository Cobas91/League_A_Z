package com.example.lol_a_z_backend.service;

import com.example.lol_a_z_backend.controller.api.service.RiotApiService;
import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.repository.ChampionRepo;
import com.example.lol_a_z_backend.security.model.Summoner;
import com.example.lol_a_z_backend.security.repo.SummonerRepo;
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

	public ChampionService(ChampionRepo repo, RiotApiService riotApiService, SummonerRepo userRepo, InitialChampionService initialChampionService) {
		this.repo = repo;
		this.riotApiService = riotApiService;
		this.userRepo = userRepo;
		this.initialChampionService = initialChampionService;
	}

	public List<Champion> getDefaultChamps() {
		return initialChampionService.getDefaultChampions();
	}

	public List<Champion> getAllChampions() {
		Optional<Summoner> summoner = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		if (summoner.isPresent()) {
			List<Champion> champs = repo.findAllBySummonerId(summoner.get().getId());
			champs.sort(Champion.Comparators.NAME);
			return champs;
		}
		return List.of();
	}

	public void editChampion(Champion champion) {
		Optional<Summoner> summoner = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		if (summoner.isPresent()) {
			List<Champion> userChamps = summoner.get().getChampions();
			champion.setSummoner(summoner.get());
			userChamps.set(userChamps.indexOf(champion), champion);
			userRepo.save(summoner.get());
		}
	}

	//TODO Methode in Repo auslagern und via Repositoy funktionalität umsetzen
	public List<Champion> getChampionsFilteredByAttribute(boolean filteredBy) {
		List<Champion> allChamps = repo.findAll();
		allChamps.sort(Champion.Comparators.NAME);
		return allChamps.stream().filter(e -> e.isPlayed() == filteredBy).collect(Collectors.toList());
	}

	//TODO Methode in Repo auslagern und via Repositoy funktionalität umsetzen
	public Champion getRandomChampionIsNotPlayed() {
		List<Champion> allChamps = repo.findAll();
		List<Champion> champsPlayable = allChamps.stream().filter(e -> !e.isPlayed()).collect(Collectors.toList());
		Collections.shuffle(champsPlayable);
		return champsPlayable.get(0);
	}

	public List<Champion> resetAllChampions() {
		List<Champion> allChamps = repo.findAll();
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

}
