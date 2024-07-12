package dev.patika.librarymanagementapi.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.book.Book;
import dev.patika.librarymanagementapi.entities.book.BookMapper;
import dev.patika.librarymanagementapi.entities.book.BookResponseDto;
import dev.patika.librarymanagementapi.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                             .stream()
                             .map(BookMapper::BookToBookResponseDto)
                             .collect(Collectors.toList());
    }

    public BookResponseDto getBookById(int id) {
        Book book = bookRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException("Book Not Found with id: " + id));
        return BookMapper.BookToBookResponseDto(book);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book Not Found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
