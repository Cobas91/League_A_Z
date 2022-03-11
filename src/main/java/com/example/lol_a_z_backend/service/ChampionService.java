package com.example.lol_a_z_backend.service;

import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.repository.ChampionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
