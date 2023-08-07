package com.example.order.reader.handler;

import com.example.order.reader.enums.TypeFile;
import com.example.order.reader.service.order.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class HandlerFile implements ApplicationRunner {
    private final OrderService orderService;

    private final ObjectMapper objectMapper;

    @Value("${reader.path}")
    private String inputPath;

    public HandlerFile(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            List<String> lines = readFiles(inputPath);
            final var customerOrders = this.orderService.processFile(lines, TypeFile.FILE_TEXT);
            String json = objectMapper
                    .writeValueAsString(customerOrders);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readFiles(String path) throws IOException {
        List<String> fileContents = new ArrayList<>();

        File fileOrDir = new File(path);

        if (fileOrDir.isDirectory()) {
            File[] files = fileOrDir.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    fileContents.addAll(readLinesFromFile(file));
                }
            }
        } else if (fileOrDir.isFile()) {
            fileContents.addAll(readLinesFromFile(fileOrDir));
        }
        return fileContents;
    }

    private static List<String> readLinesFromFile(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

}



