package dev.patika.librarymanagementapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowing;
import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowingResponseDto;
import dev.patika.librarymanagementapi.services.BookBorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookborrowings")
@RequiredArgsConstructor
public class BookBorrowingController {

    private final BookBorrowingService bookBorrowingService;

    @GetMapping
    public List<BookBorrowingResponseDto> getAllBookBorrowings() {
        return bookBorrowingService.getAllBookBorrowings();
    }

    @GetMapping("/{id}")
    public BookBorrowingResponseDto getBookBorrowingById(@PathVariable int id) {
        return bookBorrowingService.getBookBorrowingById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookBorrowing createBookBorrowing(@Valid @RequestBody BookBorrowing bookBorrowing) {
        return bookBorrowingService.saveBookBorrowing(bookBorrowing);
    }

    @PutMapping("/{id}")
    public BookBorrowing updateBookBorrowing(@PathVariable int id, @Valid @RequestBody BookBorrowing bookBorrowing) {
        bookBorrowing.setId(id);
        return bookBorrowingService.saveBookBorrowing(bookBorrowing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookBorrowing(@PathVariable int id) {
        bookBorrowingService.deleteBookBorrowing(id);
    }
}
