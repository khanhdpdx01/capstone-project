package io.github.khanhdpdx01.backend.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
    public static void save(Path fileDirectory, MultipartFile file) {
        try {
            Files.write(fileDirectory, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Resource load(Path fileDirectory, String filename) {
        try {
            Path file = fileDirectory.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static byte[] loading(Path fileDirectory, String filename) {
        Path file = fileDirectory.resolve(filename);
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getFilenameArray(List<MultipartFile> multipartFiles) {
        return multipartFiles
                .stream()
                .map(multipartFile -> multipartFile.getOriginalFilename())
                .collect(Collectors.toList());
    }
}
