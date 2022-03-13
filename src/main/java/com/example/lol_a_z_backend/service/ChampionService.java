package com.example.lol_a_z_backend.service;

import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.repository.ChampionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChampionService {
	ChampionRepo repo;

	public ChampionService(ChampionRepo repo) {
		this.repo = repo;
	}

	public void addListOfChampions(List<Champion> champs){
		repo.saveAll(champs);
	}

	public List<Champion> getAllChampions() {
		List<Champion> allChamps = repo.findAll();
		allChamps.sort(Champion.Comparators.NAME);
		return allChamps;
	}

	public void editChampion(Champion champion) {
		repo.save(champion);
	}

	public List<Champion> getChampionsFilteredByAttribute(boolean filteredBy) {
		List<Champion> allChamps = repo.findAll();
		allChamps.sort(Champion.Comparators.NAME);
		return allChamps.stream().filter(e -> e.isPlayed() == filteredBy).collect(Collectors.toList());
	}


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
}
