package com.example.lol_a_z_backend.controller.api.model;

import com.example.lol_a_z_backend.controller.api.exception.RiotApiGetChampionException;
import com.example.lol_a_z_backend.model.Champion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service public class RiotApi {
	RestTemplate restTemplate = new RestTemplate();

	public List<Champion> getAllChampions() throws RiotApiGetChampionException {
		ResponseEntity<ApiResponse> response = restTemplate.getForEntity("http://ddragon.leagueoflegends.com/cdn/12.5.1/data/de_DE/champion.json", ApiResponse.class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new RiotApiGetChampionException("Error while request Champions");
		}

		return new ArrayList<>(Objects.requireNonNull(response.getBody()).getData().values());
	}
}
