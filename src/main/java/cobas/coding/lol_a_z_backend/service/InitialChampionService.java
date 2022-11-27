package cobas.coding.lol_a_z_backend.service;

import cobas.coding.lol_a_z_backend.model.Champion;
import cobas.coding.lol_a_z_backend.controller.api.service.RiotApiService;
import cobas.coding.lol_a_z_backend.model.SystemInformation;
import cobas.coding.lol_a_z_backend.util.FileSystemUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
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
import java.util.*;

@Service @Slf4j public class InitialChampionService {
	@Autowired private SystemInformationService systemInformationService;
	private final RiotApiService riotApiService;
	private final Path champDirectoryPath = Paths.get("./data/champs/");
	private Path versionDirectory;
	private String currentVersion;

	public InitialChampionService(RiotApiService riotApiService) {
		this.riotApiService = riotApiService;
	}

	@PostConstruct private void init() {
		this.currentVersion = systemInformationService.getLatestInformation().getChampVersion();
		this.versionDirectory = Paths.get(this.champDirectoryPath + File.separator + riotApiService.getLatestVersion());
		if (currentVersionIsOlderThenRiotVersion() || FileSystemUtil.directoryIsEmpty(this.versionDirectory)) {
			checkForNewChampions();
		}
	}

	private boolean currentVersionIsOlderThenRiotVersion() {
		String currentRiotVersion = riotApiService.getLatestVersion();
		int currentInt = Integer.parseInt(this.currentVersion.replace(".", ""));
		int riotInt = Integer.parseInt(currentRiotVersion.replace(".", ""));
		return currentInt < riotInt;
	}

	@Scheduled(cron = "0 0 0 ? * TUE") private void updateChampionData() {
		init();
	}

	private void checkForNewChampions() {
		if(!Files.exists(this.champDirectoryPath)){
			FileSystemUtil.createDirectory(champDirectoryPath.toString());
		}else if(!Files.exists(this.versionDirectory)){
			FileSystemUtil.createDirectory(this.versionDirectory.toString());
		}else if(FileSystemUtil.directoryIsEmpty(this.versionDirectory)){
			startWorker();
		}else{
			this.currentVersion = riotApiService.getLatestVersion();
			systemInformationService.addInformation(SystemInformation.builder().champCount(0).champVersion(this.currentVersion).build());
		}
	}

	private void startWorker() {
		Thread task = new Thread(() -> {
			downloadInitialChamps();
			Thread.currentThread().interrupt();
		});
		task.start();
	}

	private void downloadInitialChamps() {
		List<Champion> allChamps = riotApiService.getChampionsFromApi();
		for (Champion champ : allChamps) {
			try {
				String destinationString = champDirectoryPath + File.separator + this.currentVersion;
				File destinationDirectory = new File(destinationString);
				if (!destinationDirectory.exists()) {
					FileSystemUtil.createDirectory(destinationString);
				}
				FileWriter file = new FileWriter(destinationDirectory + File.separator + champ.getName() + ".json");
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
			for (File fileEntry : Objects.requireNonNull(versionDirectory.toFile().listFiles())) {
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
	 *
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
			return champ;
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
		SystemInformation systemInformation = systemInformationService.getLatestInformation();
		String version = systemInformation.getChampVersion();
		URL imageUrl = new URL("https://ddragon.leagueoflegends.com/cdn/" + version + "/img/champion/" + champ.getId() + ".png");
		return ImageIO.read(imageUrl);
	}

	public void setCurrentVersion(String version) {
		this.currentVersion = version;
	}

}
