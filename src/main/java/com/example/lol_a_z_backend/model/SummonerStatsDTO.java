package com.example.lol_a_z_backend.model;

import com.example.lol_a_z_backend.security.model.Summoner;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SummonerStatsDTO {
	private String summonerName;
	private String championName;
	private boolean played;
	private int wins;
	private int looses;
}
