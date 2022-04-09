package com.example.lol_a_z_backend.util;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service public class FileSystemUtil {

	public boolean directoryIsEmpty(Path path) throws IOException {
		if (Files.isDirectory(path)) {
			try (Stream<Path> entries = Files.list(path)) {
				return entries.findFirst().isEmpty();
			}
		}
		return false;
	}

	public boolean createDirectory(String path) {
		File dir = new File(path);
		return dir.mkdirs();
	}

}
