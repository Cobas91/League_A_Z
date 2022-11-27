package cobas.coding.lol_a_z_backend.controller;

import cobas.coding.lol_a_z_backend.service.ChampionService;
import cobas.coding.lol_a_z_backend.model.Champion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
		return service.getAllChampions();
	}

	@PostMapping("/champions/edit")
	public void editChampion(@RequestBody Champion champion){
		service.editChampion(champion);
	}

	@GetMapping("/champions/played/{played}")
	public List<Champion> getChampionsFilteredByAttribute(@PathVariable boolean played){
		return service.getChampionsFilteredByAttribute(played);
	}

	@GetMapping("/champions/random") public Champion getRandomChampionIsNotPlayed() {
		return service.getRandomChampionIsNotPlayed();
	}

	@GetMapping("/champions/reset") public List<Champion> resetAllChampions() {
		return service.resetAllChampions();
	}
	@GetMapping("/champion/update")
	public List<Champion> updateChampionsToNewestVersion(){
		return service.updateChampions();
	}

}
