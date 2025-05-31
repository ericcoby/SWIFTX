package com.xenderclone.xender_clone;
import java.nio.file.Paths;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service

public class FileStorageService {

    private final Path uploadPath = Paths.get("uploads");

    public FileStorageService() {
    try {
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
    }catch (IOException e) {
        throw new RuntimeException("Failed to create upload directory", e);
     }
    }

    public String saveFile(MultipartFile file) throws IOException {
        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        String fileName = fileCode + "-" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return fileCode;
    }

    public Path getFile(String fileCode) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadPath, fileCode + "-*")) {
            for (Path entry : stream) {
                return entry;
            }
        }
        return null;
    }

    public static String storeFile(MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'storeFile'");
    }
}

