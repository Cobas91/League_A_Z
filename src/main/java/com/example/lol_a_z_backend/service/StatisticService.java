package com.example.lol_a_z_backend.service;

import com.example.lol_a_z_backend.model.StatisticDTO;
import com.example.lol_a_z_backend.security.model.Summoner;
import com.example.lol_a_z_backend.security.repo.SummonerRepo;
import com.example.lol_a_z_backend.security.service.SummonerDetailService;
import com.example.lol_a_z_backend.util.StatisticMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
	private ChampionService championService;
	private StatisticMapper mapper;

	public StatisticService(ChampionService championService, StatisticMapper mapper) {
		this.championService = championService;
		this.mapper = mapper;
	}

	public StatisticDTO getStatistics(){
		return mapper.mapToStatistic(championService.getChampionStats());
	}
}
