package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileController {
    @Value("${storage.file.directory}")
    private String fileDirectory;

    @GetMapping(value = "/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = FileUtil.load(Paths.get(fileDirectory), filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"").contentType(MediaType.IMAGE_JPEG).body(file);
    }
}
