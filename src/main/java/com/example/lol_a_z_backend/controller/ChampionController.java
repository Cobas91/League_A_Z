package com.example.lol_a_z_backend.controller;

import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.service.ChampionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@GetMapping("/champions")
	public List<Champion> getAllChampions(){
		log.info("Got Request for all Champions" + " and User " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return service.getAllChampions();
	}

	@PostMapping("/champions/edit")
	public void editChampion(@RequestBody Champion champion){
		log.info("Edit Champion " + champion.getName() + " and User " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		service.editChampion(champion);
	}

	@GetMapping("/champions/played/{played}")
	public List<Champion> getChampionsFilteredByAttribute(@PathVariable boolean played){
		log.info("Got Request for all Champions filtered by played " + played + " and User " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return service.getChampionsFilteredByAttribute(played);
	}

	@GetMapping("/champions/random") public Champion getRandomChampionIsNotPlayed() {
		log.info("Got Request for Random Champion" + " and User " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return service.getRandomChampionIsNotPlayed();
	}

	@GetMapping("/champions/reset") public List<Champion> resetAllChampions() {
		log.info("Git Request to reset all Champs" + " and User " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		return service.resetAllChampions();
	}

}
