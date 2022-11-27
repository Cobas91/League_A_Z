package cobas.coding.lol_a_z_backend.controller.api.controller;

import cobas.coding.lol_a_z_backend.controller.api.exception.RiotApiGetChampionException;
import cobas.coding.lol_a_z_backend.controller.api.model.RiotApiResponse;
import cobas.coding.lol_a_z_backend.model.Champion;
import cobas.coding.lol_a_z_backend.model.SystemInformation;
import cobas.coding.lol_a_z_backend.repository.SystemInformationRepository;
import cobas.coding.lol_a_z_backend.service.InitialChampionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service @Slf4j public class RiotApi {
	@Autowired
	SystemInformationRepository systemInformationRepository;
	RestTemplate restTemplate = new RestTemplate();
	@Autowired ApplicationContext context;

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
		Collection<Champion> data = Objects.requireNonNull(response.getBody()).getData().values();
		for (Champion champ : data) {
			champ.setVersion(version[0]);
		}
		saveSystemStatus(data.size(), version[0]);
		context.getBean(InitialChampionService.class).setCurrentVersion(version[0]);
		return new ArrayList<>(data);
	}

	public void saveSystemStatus(int dataSize, String version){
		SystemInformation systemInformation = SystemInformation.builder().champCount(dataSize).champVersion(version).build();
		systemInformationRepository.save(systemInformation);
	}

	public String[] getVersions() throws RiotApiGetChampionException {
		ResponseEntity<String[]> response = restTemplate.getForEntity("https://ddragon.leagueoflegends.com/api/versions.json", String[].class);
		if (response.getStatusCode() != HttpStatus.OK) {
			throw new RiotApiGetChampionException("Error while request Champions");
		}
		String[] versions = response.getBody();
		log.info("League of Legends version: "+versions[0]);
		return versions;
	}
}
