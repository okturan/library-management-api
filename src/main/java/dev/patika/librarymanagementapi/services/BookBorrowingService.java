package dev.patika.librarymanagementapi.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.book.Book;
import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowing;
import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowingMapper;
import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowingResponseDto;
import dev.patika.librarymanagementapi.repositories.BookBorrowingRepository;
import dev.patika.librarymanagementapi.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookBorrowingService {

    private final BookBorrowingRepository bookBorrowingRepository;
    private final BookRepository bookRepository;

    public List<BookBorrowingResponseDto> getAllBookBorrowings() {
        return bookBorrowingRepository.findAll()
                                      .stream()
                                      .map(BookBorrowingMapper::bookBorrowingToBookBorrowingResponseDto)
                                      .collect(Collectors.toList());
    }

    public BookBorrowingResponseDto getBookBorrowingById(int id) {
        BookBorrowing bookBorrowing = bookBorrowingRepository.findById(id)
                                                             .orElseThrow(() -> new EntityNotFoundException(
                                                                     "Book borrowing not found with id: " + id));
        return BookBorrowingMapper.bookBorrowingToBookBorrowingResponseDto(bookBorrowing);
    }

    public BookBorrowing saveBookBorrowing(BookBorrowing bookBorrowing) {
        // Fetch the complete book information using the ID
        Book book = bookRepository.findById(bookBorrowing.getBook().getId())
                                  .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookBorrowing.getBook().getId()));

        // Check if the book is in stock
        if (book.getStock() <= 0) {
            throw new IllegalArgumentException("The book is out of stock and cannot be borrowed.");
        }

        // Set the fetched book to the bookBorrowing object
        bookBorrowing.setBook(book);

        // Decrease the stock
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        // Save the borrowing
        return bookBorrowingRepository.save(bookBorrowing);
    }

    public void deleteBookBorrowing(int id) {
        if (!bookBorrowingRepository.existsById(id)) {
            throw new EntityNotFoundException("Book borrowing not found with id: " + id);
        }
        bookBorrowingRepository.deleteById(id);
    }
}
