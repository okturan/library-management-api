package dev.patika.librarymanagementapi;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.patika.librarymanagementapi.entities.book.Book;
import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowing;
import dev.patika.librarymanagementapi.repositories.BookBorrowingRepository;
import dev.patika.librarymanagementapi.repositories.BookRepository;
import dev.patika.librarymanagementapi.services.BookBorrowingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookBorrowingServiceTests {

    @Mock
    private BookBorrowingRepository borrowingRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookBorrowingService service;

    @Test
    void creatingABorrowingDecrementsStockOnce() {
        Book book = book(7, 2);
        BookBorrowing borrowing = borrowing(0, book);
        when(bookRepository.findById(7)).thenReturn(Optional.of(book));
        when(borrowingRepository.save(borrowing)).thenReturn(borrowing);

        service.createBookBorrowing(borrowing);

        assertEquals(1, book.getStock());
        verify(bookRepository).save(book);
        verify(borrowingRepository).save(borrowing);
    }

    @Test
    void updatingTheSameBorrowedBookDoesNotDecrementStockAgain() {
        Book book = book(7, 2);
        BookBorrowing existing = borrowing(4, book);
        BookBorrowing update = borrowing(0, book(7, 0));
        when(borrowingRepository.findById(4)).thenReturn(Optional.of(existing));
        when(bookRepository.findById(7)).thenReturn(Optional.of(book));
        when(borrowingRepository.save(update)).thenReturn(update);

        service.updateBookBorrowing(4, update);

        assertEquals(2, book.getStock());
        assertEquals(4, update.getId());
        verify(bookRepository, never()).save(book);
    }

    @Test
    void creatingABorrowingRejectsOutOfStockBooks() {
        Book book = book(7, 0);
        BookBorrowing borrowing = borrowing(0, book);
        when(bookRepository.findById(7)).thenReturn(Optional.of(book));

        assertThrows(IllegalArgumentException.class, () -> service.createBookBorrowing(borrowing));

        verify(bookRepository, never()).save(book);
        verify(borrowingRepository, never()).save(borrowing);
    }

    private static Book book(int id, int stock) {
        Book book = new Book();
        book.setId(id);
        book.setStock(stock);
        return book;
    }

    private static BookBorrowing borrowing(int id, Book book) {
        BookBorrowing borrowing = new BookBorrowing();
        borrowing.setId(id);
        borrowing.setBook(book);
        return borrowing;
    }
}
