package com.example.lol_a_z_backend.service;

import com.example.lol_a_z_backend.controller.api.service.RiotApiService;
import com.example.lol_a_z_backend.model.Champion;
import com.example.lol_a_z_backend.util.FileSystemUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service @Slf4j public class InitialChampionService {
	private final RiotApiService riotApiService;
	private final Path jsonFilePath = Paths.get("./data/champs/");
	private final FileSystemUtil fileSystemUtil;

	public InitialChampionService(RiotApiService riotApiService, FileSystemUtil fileSystemUtil) {
		this.riotApiService = riotApiService;
		this.fileSystemUtil = fileSystemUtil;
		try {
			if (!Files.exists(jsonFilePath)) {
				if (fileSystemUtil.createDirectory(jsonFilePath.toString())) {
					downloadInitialChamps();
				}
			} else if (fileSystemUtil.directoryIsEmpty(jsonFilePath)) {
				downloadInitialChamps();
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void downloadInitialChamps() {
		List<Champion> allChamps = riotApiService.getChampionsFromApi();
		for (Champion champ : allChamps) {
			try {
				FileWriter file = new FileWriter(jsonFilePath + "/" + champ.getName() + ".json");
				Gson gson = new Gson();
				String json = gson.toJson(setIconByteArray(champ));
				log.info("Writing champion to file: " + champ.getId());
				file.write(json);
				file.flush();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

	}

	public List<Champion> getDefaultChampions() {
		ObjectMapper mapper = new ObjectMapper();
		List<Champion> champs = new ArrayList<>();
		try {
			for (File fileEntry : Objects.requireNonNull(jsonFilePath.toFile().listFiles())) {
				Champion champ = mapper.readValue(new File(String.valueOf(fileEntry)), Champion.class);
				champs.add(champ);
			}
			return champs;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return List.of();
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

	private byte[] getByteArrayForImage(BufferedImage image) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", byteArrayOutputStream);
		byteArrayOutputStream.flush();
		byteArrayOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	private BufferedImage downloadChampionIcon(Champion champ) throws IOException {
		URL imageUrl = new URL("https://ddragon.leagueoflegends.com/cdn/12.5.1/img/champion/" + champ.getId() + ".png");
		return ImageIO.read(imageUrl);
	}

}
