package com.example.lol_a_z_backend.controller.api.controller;

import com.example.lol_a_z_backend.controller.api.exception.RiotApiGetChampionException;
import com.example.lol_a_z_backend.controller.api.model.RiotApiResponse;
import com.example.lol_a_z_backend.model.Champion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service @Slf4j public class RiotApi {
	RestTemplate restTemplate = new RestTemplate();

	/**
	 * Fetch a .json file from the Riot API URL.
	 *
	 * @return List of Champions
	 * @throws RiotApiGetChampionException
	 */
	public List<Champion> getAllChampions() throws RiotApiGetChampionException {
		String[] version = getVersions();
		ResponseEntity<RiotApiResponse> response = restTemplate.getForEntity("http://ddragon.leagueoflegends.com/cdn/"+version[0]+"/data/en_US/champion.json", RiotApiResponse.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new RiotApiGetChampionException("Error while request Champions");
		}
		return new ArrayList<>(Objects.requireNonNull(response.getBody()).getData().values());
	}

	private String[] getVersions() throws RiotApiGetChampionException {
		ResponseEntity<String[]> response = restTemplate.getForEntity("https://ddragon.leagueoflegends.com/api/versions.json", String[].class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new RiotApiGetChampionException("Error while request Champions");
		}
		String[] versions = response.getBody();
		log.info("Newest League of Legends version: "+versions[0]);
		return versions;
	}
}
