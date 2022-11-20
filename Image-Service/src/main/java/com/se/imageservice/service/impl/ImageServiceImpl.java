package com.se.imageservice.service.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.se.imageservice.dto.ImageDTO;
import com.se.imageservice.model.Image;
import com.se.imageservice.repository.ImageRepository;
import com.se.imageservice.service.ImageService;
import org.apache.maven.InternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.net.URLEncoder.encode;

@Service
public class ImageServiceImpl implements ImageService {
    
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/ecommerce-app-74670.appspot.com/o/%s?alt=media&token=%s";

    @Autowired
    private ImageRepository imageRepository;
    
    @Override
    @CachePut(value = "Image")
    public ImageDTO upload(MultipartFile multipartFile, Long productId) throws InternalErrorException {
        try {
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));
            File file = this.convertToFile(multipartFile, fileName);
            String url = this.uploadFile(file, fileName);
            file.delete();
            
            Image image = new Image();
            image.setFileURL(url);
            image.setProductId(productId);
            return ImageDTO.build(this.imageRepository.save(image));
            
        } catch (IOException | InternalErrorException e) {
            e.printStackTrace();
            throw new InternalErrorException("Error when upload file", e.getCause());
        }
    }
    
    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("ecommerce-app-74670.appspot.com", fileName);
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map).setContentType("media").build();
        Credentials credentials = GoogleCredentials
                .fromStream(Files.newInputStream(Paths.get("D:\\KTTKPM\\Project-Microservice-KTTKPM\\Image-Service\\src\\main\\resources\\static\\credentials.json")));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, encode(fileName, StandardCharsets.UTF_8), encode(fileName, StandardCharsets.UTF_8));
    }
    
    private File convertToFile(MultipartFile multipartFile, String fileName) throws InternalErrorException {
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new InternalErrorException("Error converting multipartFile to file", e.getCause());
        }
        return file;
    }
    
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
