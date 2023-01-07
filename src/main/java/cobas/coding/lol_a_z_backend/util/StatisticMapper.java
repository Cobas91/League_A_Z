package cobas.coding.lol_a_z_backend.util;

import cobas.coding.lol_a_z_backend.model.StatisticDTO;
import cobas.coding.lol_a_z_backend.model.SummonerStatsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class StatisticMapper {

	public StatisticDTO mapToStatistic(List<SummonerStatsDTO> list) {
		return StatisticDTO.builder().summoners(list).build();
	}
}
