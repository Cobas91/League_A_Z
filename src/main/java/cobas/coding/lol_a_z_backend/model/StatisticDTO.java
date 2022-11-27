package cobas.coding.lol_a_z_backend.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatisticDTO {
	private List<SummonerStatsDTO> summoners;
}
