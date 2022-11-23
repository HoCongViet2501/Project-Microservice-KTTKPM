package com.se.imageservice.service;

import com.se.imageservice.dto.ImageDTO;
import org.apache.maven.InternalErrorException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageDTO upload(MultipartFile multipartFile) throws InternalErrorException;
    
}
