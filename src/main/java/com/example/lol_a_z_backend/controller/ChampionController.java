package com.example.lol_a_z_backend.controller;

import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.service.ChampionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ChampionController {
	ChampionService service;

	public ChampionController(ChampionService service) {
		this.service = service;
	}

	@PostMapping("/champions")
	public void addListOfChampions(@RequestBody List<Champion> champions){
		service.addListOfChampions(champions);
		log.info("Add List of Champions "+ champions);
	}


	@GetMapping("/champions")
	public List<Champion> getAllChampions(){
		log.info("Got Request for all Champions");
		return service.getAllChampions();
	}

	@PostMapping("/champions/edit")
	public void editChampion(@RequestBody Champion champion){
		log.info("Edit Champion "+ champion.getName());
		service.editChampion(champion);
	}

	@GetMapping("/champions/played/{played}")
	public List<Champion> getChampionsFilteredByAttribute(@PathVariable boolean played){
		return service.getChampionsFilteredByAttribute(played);
	}

	@GetMapping("/champion/random")
	public Champion getRandomChampionIsNotPlayed(){
		return service.getRandomChampionIsNotPlayed();
	}
}
