package org.springproject.library.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springproject.library.dto.BookDto;
import org.springproject.library.dto.ReserveDto;
import org.springproject.library.entity.BookEntity;
import org.springproject.library.entity.ReserveEntity;
import org.springproject.library.entity.UserEntity;
import org.springproject.library.service.BookService;
import org.springproject.library.service.ReserveService;
import org.springproject.library.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springproject.library.utils.Constants.ADMIN_ROLE;
import static org.springproject.library.utils.Constants.USER_ROLE;

@RestController
@RequestMapping("/api/v1/library/reserve")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;
    @Autowired private BookService bookService;
    @Autowired private UserService userService;

    @GetMapping("/list")
    public ResponseEntity getAllReserve(){
        List<ReserveDto> listReserves = reserveService.getAllReserves().stream()
                .map(reserve -> new ReserveDto(reserve)).collect(Collectors.toList());
        return ResponseEntity.ok(listReserves);
    }

    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @PostMapping("/create")
    public ResponseEntity addReserve(@RequestBody ReserveDto reserveDto){
        ReserveEntity reserveNew = new ReserveEntity();
        Optional<UserEntity> userFind = userService.getUserById(reserveDto.getIdUser());
        Optional <BookEntity> bookFind = bookService.getBookById(reserveDto.getIdBook());
        if(!userFind.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if( !bookFind.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(bookFind.get().isStatusReserveBook()==true){
            return ResponseEntity.ok().body("El libro ya esta reservado");
        }
        reserveNew.setIdBook(bookFind.get().getId());
        reserveNew.setIdUser(userFind.get().getId());
        reserveNew.setReservationStartDate(reserveDto.getReservationStartDate());
        reserveNew.setStateReserve("RESERVED");
        bookFind.get().setStatusReserveBook(true);
        bookService.updateBook(bookFind.get());
        ReserveDto reserveNewDto = new ReserveDto(reserveService.addReserve(reserveNew));
        return ResponseEntity.ok(reserveNewDto);
    }
}
