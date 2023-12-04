package com.agent47.imagesavingdatabase.service;

import com.agent47.imagesavingdatabase.entity.ImageData;
import com.agent47.imagesavingdatabase.repository.StorageRepository;
import com.agent47.imagesavingdatabase.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;
    public String uploadImage(MultipartFile file) throws IOException {
       ImageData imageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build();
       ImageData imageData1=storageRepository.save(imageData);
       if(imageData1!= null) {
           return "Successful";
       }
       else
           return "Unsuccessful";
    }
    @Transactional
    public byte[] downloadImage(String fileName){
        ImageData dbImageData = storageRepository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.getImageData());
        return images;
    }
}
