package cobas.coding.lol_a_z_backend.service;

import cobas.coding.lol_a_z_backend.model.StatisticDTO;
import cobas.coding.lol_a_z_backend.util.StatisticMapper;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
	private final ChampionService championService;
	private final StatisticMapper mapper;

	public StatisticService(ChampionService championService, StatisticMapper mapper) {
		this.championService = championService;
		this.mapper = mapper;
	}

	public StatisticDTO getStatistics(){
		return mapper.mapToStatistic(championService.getChampionStats());
	}
}
