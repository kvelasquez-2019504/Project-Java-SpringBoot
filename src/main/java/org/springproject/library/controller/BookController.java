package org.springproject.library.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springproject.library.dto.BookDto;
import org.springproject.library.entity.BookEntity;
import org.springproject.library.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springproject.library.utils.Constants.ADMIN_ROLE;

@RestController
@RequestMapping("/api/v1/library/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RolesAllowed(ADMIN_ROLE)
    @PostMapping("/")
    public ResponseEntity createBook(@RequestBody BookDto bookDto){
        BookEntity bookNew = new BookEntity();
        bookNew.setTitle(bookDto.getTitle());
        bookNew.setAuthor(bookDto.getAuthor());
        bookNew.setDescription(bookDto.getDescription());
        bookNew.setPublishedDate(bookDto.getPublishedDate());
        bookNew.setStatusReserveBook(false);
        bookNew.setStatusBook(true);
        BookDto bookDtoNew = new BookDto(bookService.addBook(bookNew));
        return ResponseEntity.ok(bookDtoNew);
    }

    @GetMapping("/")
    public ResponseEntity getAllBook(){
        List<BookDto> listBooks = bookService.getAllBooks().stream()
                .map(book -> new BookDto(book)).collect(Collectors.toList());
        return ResponseEntity.ok(listBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookById(@PathVariable String id){
        Optional<BookDto> bookSearch = bookService.getBookById(id).map(book->new BookDto(book));
        if (bookSearch.isPresent()) {
            return ResponseEntity.ok(bookSearch.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @PutMapping("/{id}")
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody BookDto bookDto){
        Optional<BookEntity> bookSearch = bookService.getBookById(id);
        if ( bookSearch.isPresent()) {
            bookSearch.get().setAuthor(bookDto.getAuthor());
            bookSearch.get().setTitle(bookDto.getTitle());
            bookSearch.get().setDescription(bookDto.getDescription());
            bookSearch.get().setPublishedDate(bookDto.getPublishedDate());
            BookDto bookDtoUpdated = new BookDto(bookService.updateBook(bookSearch.get()));
            return ResponseEntity.ok(bookDtoUpdated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){
        Optional<BookEntity> bookSearch = bookService.getBookById(id);
        if ( bookSearch.isPresent()) {
            bookSearch.get().setStatusBook(false);
            return ResponseEntity.ok(bookService.updateBook(bookSearch.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
