package dev.patika.librarymanagementapi.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowing;
import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowingMapper;
import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowingResponseDto;
import dev.patika.librarymanagementapi.repositories.BookBorrowingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookBorrowingService {

    private final BookBorrowingRepository bookBorrowingRepository;

    public List<BookBorrowingResponseDto> getAllBookBorrowings() {
        return bookBorrowingRepository.findAll()
                                      .stream()
                                      .map(BookBorrowingMapper::bookBorrowingToBookBorrowingResponseDto)
                                      .collect(Collectors.toList());
    }

    public BookBorrowingResponseDto getBookBorrowingById(int id) {
        BookBorrowing bookBorrowing = bookBorrowingRepository.findById(id)
                                                             .orElseThrow(
                                                                     () -> new EntityNotFoundException(
                                                                             "Book borrowing not found with id: " +
                                                                             id));
        return BookBorrowingMapper.bookBorrowingToBookBorrowingResponseDto(bookBorrowing);
    }

    public BookBorrowing saveBookBorrowing(BookBorrowing bookBorrowing) {
        return bookBorrowingRepository.save(bookBorrowing);
    }

    public void deleteBookBorrowing(int id) {
        if (!bookBorrowingRepository.existsById(id)) {
            throw new EntityNotFoundException("Book borrowing not found with id: " + id);
        }
        bookBorrowingRepository.deleteById(id);
    }
}
