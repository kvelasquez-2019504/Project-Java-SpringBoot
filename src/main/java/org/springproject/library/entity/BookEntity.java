package org.springproject.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class BookEntity {
    @Id
    private String id;
    private String author;
    private String title;
    private String description;
    private Date publishedDate;
    private boolean statusReserveBook;
    private boolean statusBook;
}
