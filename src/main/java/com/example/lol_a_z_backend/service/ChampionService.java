package com.example.lol_a_z_backend.service;

import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.repository.ChampionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
		return repo.findAll();
	}

	public void editChampion(Champion champion) {
		repo.save(champion);
	}

	public List<Champion> getChampionsFilteredByAttribute(boolean filteredBy) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<Champion> toFind = Example.of(Champion.builder().played(filteredBy).build(), matcher);
		log.info(String.valueOf(toFind));
		return repo.findAll(toFind);
	}

	public Champion getRandomChampionIsNotPlayed() {
		Champion champ = new Champion();
		champ.setPlayed(true);
		Example<Champion> toFind = Example.of(champ);
		List<Champion> allChampsToPlay = repo.findAll(toFind);
		Random rng = new Random();
		return allChampsToPlay.get(rng.nextInt(allChampsToPlay.size()));
	}
}
