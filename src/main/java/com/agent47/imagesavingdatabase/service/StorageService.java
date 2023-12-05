package com.agent47.imagesavingdatabase.service;

import com.agent47.imagesavingdatabase.entity.FileData;
import com.agent47.imagesavingdatabase.entity.ImageData;
import com.agent47.imagesavingdatabase.repository.FileDataRespository;
import com.agent47.imagesavingdatabase.repository.StorageRepository;
import com.agent47.imagesavingdatabase.util.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StorageService {
    private StorageRepository storageRepository;
    private FileDataRespository fileDataRespository;
    final private String FOLDER_PATH="E:/other-practices/image-saving-database/storing-files";
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
    public String uploadFile(MultipartFile file) throws IOException {
        String filepath = FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filepath)
                .build();
        FileData fileData1=fileDataRespository.save(fileData);
        file.transferTo(new File(filepath));
        if(fileData1!= null) {
            return "Successful: "+ filepath;
        }
        else
            return "Unsuccessful";
    }
    @Transactional
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        FileData fileData = fileDataRespository.findByName(fileName);
        String filPath =fileData.getFilePath();
        byte[] images= Files.readAllBytes(new File(filPath).toPath());
        return images;
    }

}
