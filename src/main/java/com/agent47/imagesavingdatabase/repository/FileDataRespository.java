package com.agent47.imagesavingdatabase.repository;

import com.agent47.imagesavingdatabase.entity.FileData;
import com.agent47.imagesavingdatabase.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDataRespository extends JpaRepository<FileData,Long> {
    FileData findByName(String fileName);
}
