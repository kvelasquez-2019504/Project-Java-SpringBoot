package org.springproject.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springproject.library.entity.BookEntity;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String author;
    private String title;
    private String description;
    private Date publishedDate;

    public BookDto(BookEntity book) {
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.publishedDate = book.getPublishedDate();
    }
}
