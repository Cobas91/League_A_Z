package com.example.lol_a_z_backend.service;

import com.example.lol_a_z_backend.controller.api.service.RiotApiService;
import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.repository.ChampionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j public class ChampionService {
	ChampionRepo repo;
	RiotApiService riotApiService;

	public ChampionService(ChampionRepo repo, RiotApiService riotApiService) {
		this.repo = repo;
		this.riotApiService = riotApiService;

		if (repo.findAll().isEmpty()) {
			getNewChampionsFromRiotApi();
		}
	}

	public List<Champion> getAllChampions() {
		List<Champion> allChamps = repo.findAll();
		allChamps.sort(Champion.Comparators.NAME);
		return allChamps;
	}

	public void editChampion(Champion champion) {
		repo.save(champion);
	}

	//TODO Methode in Repo auslagern und via Repositoy funktionalität umsetzen
	public List<Champion> getChampionsFilteredByAttribute(boolean filteredBy) {
		List<Champion> allChamps = repo.findAll();
		allChamps.sort(Champion.Comparators.NAME);
		return allChamps.stream().filter(e -> e.isPlayed() == filteredBy).collect(Collectors.toList());
	}

	//TODO Methode in Repo auslagern und via Repositoy funktionalität umsetzen
	public Champion getRandomChampionIsNotPlayed() {
		List<Champion> allChamps = repo.findAll();
		List<Champion> champsPlayable = allChamps.stream().filter(e -> !e.isPlayed()).collect(Collectors.toList());
		Collections.shuffle(champsPlayable);
		return champsPlayable.get(0);
	}

	public List<Champion> resetAllChampions() {
		List<Champion> allChamps = repo.findAll();
		List<Champion> resetedChamps = new ArrayList<>();
		for (Champion champ : allChamps) {
			champ.setPlayed(false);
			champ.setLoose(0);
			champ.setWins(0);
			resetedChamps.add(champ);
		}
		return repo.saveAll(resetedChamps);
	}

	/**
	 * Download the Icon from the RIOT Api Page and calculate the Base64 byte array.
	 * Setting this icon as jpg to the Champion
	 *
	 * @param champ
	 * @return {@link Champion}
	 */
	public Champion setIconByteArray(Champion champ) {
		try {
			log.info("Generating IconByteArray for: " + champ.getId());
			byte[] imageByteArray = getByteArrayForImage(downloadChampionIcon(champ));
			String fileName = champ.getId() + ".jpg";
			String imageType = "image/jpg";

			MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, imageType, imageByteArray);
			champ.setIconByteArray(multipartFile.getBytes());
			return champ;
		} catch (IOException e) {
			log.error("Cant set Byte Array for " + champ.getId() + " " + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Helper function for {@link #setIconByteArray(Champion)}
	 *
	 * @param image
	 * @return Base64 Byte Array
	 * @throws IOException
	 */
	private byte[] getByteArrayForImage(BufferedImage image) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", byteArrayOutputStream);
		byteArrayOutputStream.flush();
		byteArrayOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * Helper method for {@link #setIconByteArray(Champion)}
	 *
	 * @param champ
	 * @return
	 * @throws IOException
	 */
	private BufferedImage downloadChampionIcon(Champion champ) throws IOException {
		URL imageUrl = new URL("https://ddragon.leagueoflegends.com/cdn/12.5.1/img/champion/" + champ.getId() + ".png");
		return ImageIO.read(imageUrl);
	}

	/**
	 * Uses the Service to fetch Champions and set the Base64 Icons
	 *
	 * @return List of Champions with Icons
	 */
	public List<Champion> getNewChampionsFromRiotApi() {
		List<Champion> allChamps = riotApiService.getChampionsFromApi();
		List<Champion> allChampsWithImage = new ArrayList<>();
		for (Champion champ : allChamps) {
			allChampsWithImage.add(setIconByteArray(champ));
		}
		return repo.saveAll(allChampsWithImage);

	}
}
