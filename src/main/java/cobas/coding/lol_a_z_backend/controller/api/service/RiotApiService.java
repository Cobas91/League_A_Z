package cobas.coding.lol_a_z_backend.controller.api.service;

import cobas.coding.lol_a_z_backend.controller.api.controller.RiotApi;
import cobas.coding.lol_a_z_backend.controller.api.exception.RiotApiGetChampionException;
import cobas.coding.lol_a_z_backend.controller.api.model.RiotApiResponse;
import cobas.coding.lol_a_z_backend.model.Champion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j public class RiotApiService {
	private final RiotApi riotApi;
	private ApplicationContext context;

	public RiotApiService(RiotApi riotApi) {
		this.riotApi = riotApi;
	}

	/**
	 * Fetch a list of Champions from the Riot API. No API Key needed for this fetch.
	 *
	 * @return List of Champions without icon Data.
	 *
	 * @see RiotApiResponse
	 */
	public List<Champion> getChampionsFromApi() {
		try {
			return riotApi.getAllChampions();
		} catch (RiotApiGetChampionException e) {
			log.error("Cant fetch Champion data from Riot Api" + e.getMessage(), e);
		}
		return List.of();
	}

	public String getLatestVersion() {
		try {
			String[] versions = riotApi.getVersions();
			return versions[0];
		} catch (RiotApiGetChampionException e) {
			log.error(e.getMessage(), e);
			return "0";
		}
	}

}
