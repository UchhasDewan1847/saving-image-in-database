package com.agent47.imagesavingdatabase.repository;

import com.agent47.imagesavingdatabase.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    ImageData findByName(String file);
}
