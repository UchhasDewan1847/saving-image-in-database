package com.agent47.imagesavingdatabase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FileData")
@Data


public class FileData {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;

    private String filePath;
}
