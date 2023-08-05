package com.example.orderreader.enums;

import org.springframework.web.multipart.MultipartFile;

import java.lang.constant.Constable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public enum TypeFile {
    FILE_TEXT("txt"),
    FILE_CSV("csv");

    private String type;

    TypeFile(final String type) {
        this.type = type;
    }

    public static TypeFile getFileByExtension(final MultipartFile file) {
        Path filePath = Paths.get(file.getOriginalFilename());
        final var typeFile =  filePath.getFileName().toString().split("\\.")[1].toLowerCase();

        return Arrays.stream(TypeFile.values())
                .filter(item ->  item.type.equals(typeFile))
                .findFirst()
                .orElse(null);
    }
}
