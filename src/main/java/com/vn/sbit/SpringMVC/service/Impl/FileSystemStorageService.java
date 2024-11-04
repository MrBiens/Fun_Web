package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileSystemStorageService implements StorageService {
    private static final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);
    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation=Paths.get("src/main/resources/static/uploads");
    }


    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize().toAbsolutePath();
            log.info("đã đến đây 1 ");

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                log.info("đã đến đây 2 ");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
