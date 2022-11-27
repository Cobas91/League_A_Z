package cobas.coding.lol_a_z_backend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
@Slf4j
@Service public class FileSystemUtil {

	public boolean directoryIsEmpty(Path path) {
		try {
			if (Files.isDirectory(path)) {
				try (Stream<Path> entries = Files.list(path)) {
					return entries.findFirst().isEmpty();
				}
			}
			return false;
		} catch (IOException e) {
			log.error(e.getMessage(),e);
			return false;
		}
	}

	public boolean createDirectory(String path) {
		File dir = new File(path);
		return dir.mkdirs();
	}

}
