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
@Table(name = "image_data")
@Data

public class ImageData {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "image")
    private byte[] imageData;

}
/*
In JPA, "@Lob" is an annotation used to mark a field as a large object.
This tells JPA to store the field's value in a separate database table optimized for large data,
like images or documents, instead of the main table.
 */
