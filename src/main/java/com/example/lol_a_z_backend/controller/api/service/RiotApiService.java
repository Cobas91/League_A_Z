package com.example.lol_a_z_backend.controller.api.service;

import com.example.lol_a_z_backend.controller.api.controller.RiotApi;
import com.example.lol_a_z_backend.controller.api.exception.RiotApiGetChampionException;
import com.example.lol_a_z_backend.model.Champion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j public class RiotApiService {
	private final RiotApi riotApi;

	public RiotApiService(RiotApi riotApi) {
		this.riotApi = riotApi;
	}

	public List<Champion> getChampionsFromApi() {
		try {
			return riotApi.getAllChampions();
		} catch (RiotApiGetChampionException e) {
			log.error("Cant fetch Champion data from Riot Api" + e.getMessage(), e);
		}
		return List.of();
	}
}
