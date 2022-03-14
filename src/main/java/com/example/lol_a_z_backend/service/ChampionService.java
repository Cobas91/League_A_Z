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
import java.util.Optional;
import java.util.stream.Collectors;

@Service @Slf4j public class ChampionService {
	ChampionRepo repo;
	RiotApiService riotApiService;

	public ChampionService(ChampionRepo repo, RiotApiService riotApiService) {
		this.repo = repo;
		this.riotApiService = riotApiService;
		log.info(String.valueOf(repo.findAll().size()));
		if (repo.findAll().isEmpty()) {
			getNewChampionsFromRiotApi();
		}
	}

	private boolean checkByteArrayIsPresent(String champId) {
		Optional<Champion> optChamp = repo.findById(champId);
		return optChamp.filter(champion -> champion.getIconByteArray() == null).isPresent();
	}

	public List<Champion> getAllChampions() {
		List<Champion> allChamps = repo.findAll();
		allChamps.sort(Champion.Comparators.NAME);
		return allChamps;
	}

	public void editChampion(Champion champion) {
		repo.save(champion);
	}

	public List<Champion> getChampionsFilteredByAttribute(boolean filteredBy) {
		List<Champion> allChamps = repo.findAll();
		allChamps.sort(Champion.Comparators.NAME);
		return allChamps.stream().filter(e -> e.isPlayed() == filteredBy).collect(Collectors.toList());
	}

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

	public Champion getChampionById(String id) {
		Optional<Champion> optChamp = repo.findById(id);
		return optChamp.orElse(null);
	}

	public Champion setIconByteArray(Champion champ) {
		try {
			log.info("Generating IconByteArray for: " + champ.getId());
			URL imageUrl = new URL("https://ddragon.leagueoflegends.com/cdn/12.5.1/img/champion/" + champ.getId() + ".png");
			BufferedImage image = ImageIO.read(imageUrl);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", byteArrayOutputStream);
			byteArrayOutputStream.flush();
			String fileName = champ.getId() + ".jpg";
			String imageType = "image/jpg";
			MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, imageType, byteArrayOutputStream.toByteArray());
			byteArrayOutputStream.close();
			champ.setIconByteArray(multipartFile.getBytes());
			return champ;
		} catch (IOException e) {
			log.error("Cant set Byte Array for " + champ.getId() + " " + e.getMessage(), e);
		}
		return new Champion();
	}

	public List<Champion> getNewChampionsFromRiotApi() {
		List<Champion> allChamps = riotApiService.getChampionsFromApi();
		List<Champion> allChampsWithImage = new ArrayList<>();
		for (Champion champ : allChamps) {
			allChampsWithImage.add(setIconByteArray(champ));
		}
		return repo.saveAll(allChampsWithImage);

	}
}
