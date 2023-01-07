package cobas.coding.lol_a_z_backend.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Slf4j public class FileSystemUtil {

	public static boolean directoryIsEmpty(Path path) {
		try {
			if (Files.isDirectory(path)) {
				try (Stream<Path> entries = Files.list(path)) {
					return entries.findFirst().isEmpty();
				}
			} else if (!Files.exists(path)) {
				return true;
			}
			return false;
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	public static boolean createDirectory(String path) {
		File dir = new File(path);
		return dir.mkdirs();
	}

}
