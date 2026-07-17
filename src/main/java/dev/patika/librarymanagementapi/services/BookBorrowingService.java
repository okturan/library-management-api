package dev.patika.librarymanagementapi.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
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

    @Transactional
    public BookBorrowing createBookBorrowing(BookBorrowing bookBorrowing) {
        Book book = requireAvailableBook(bookBorrowing.getBook().getId());
        bookBorrowing.setBook(book);
        decrementStock(book);
        return bookBorrowingRepository.save(bookBorrowing);
    }

    @Transactional
    public BookBorrowing updateBookBorrowing(int id, BookBorrowing update) {
        BookBorrowing existing = bookBorrowingRepository.findById(id)
                                                        .orElseThrow(() -> new EntityNotFoundException(
                                                                "Book borrowing not found with id: " + id));
        Book requestedBook = bookRepository.findById(update.getBook().getId())
                                           .orElseThrow(() -> new EntityNotFoundException(
                                                   "Book not found with id: " + update.getBook().getId()));

        if (existing.getBook().getId() != requestedBook.getId()) {
            Book previousBook = existing.getBook();
            previousBook.setStock(previousBook.getStock() + 1);
            bookRepository.save(previousBook);
            if (requestedBook.getStock() <= 0) {
                throw new IllegalArgumentException("The book is out of stock and cannot be borrowed.");
            }
            decrementStock(requestedBook);
        }

        update.setId(id);
        update.setBook(requestedBook);
        return bookBorrowingRepository.save(update);
    }

    private Book requireAvailableBook(int id) {
        Book book = bookRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        if (book.getStock() <= 0) {
            throw new IllegalArgumentException("The book is out of stock and cannot be borrowed.");
        }
        return book;
    }

    private void decrementStock(Book book) {
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBookBorrowing(int id) {
        if (!bookBorrowingRepository.existsById(id)) {
            throw new EntityNotFoundException("Book borrowing not found with id: " + id);
        }
        bookBorrowingRepository.deleteById(id);
    }
}
