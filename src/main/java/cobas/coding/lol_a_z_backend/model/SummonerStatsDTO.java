package cobas.coding.lol_a_z_backend.model;

import lombok.Builder;
import lombok.Data;

@Data @Builder public class SummonerStatsDTO {
	private String summonerName;
	private String championName;
	private boolean played;
	private int wins;
	private int looses;
}
