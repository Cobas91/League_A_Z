package com.example.lol_a_z_backend.controller.statistic;

import com.example.lol_a_z_backend.model.StatisticDTO;
import com.example.lol_a_z_backend.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic/overview/summoner")
public class StatisticController {
	private StatisticService statisticService;

	public StatisticController(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	@GetMapping
	public StatisticDTO getStatistics(){
		return statisticService.getStatistics();
	}
}
