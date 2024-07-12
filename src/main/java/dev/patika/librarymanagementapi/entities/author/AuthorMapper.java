package dev.patika.librarymanagementapi.entities.author;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.book.Book;

public class AuthorMapper {
    public static AuthorResponseDto AuthorToAuthorResponseDto(Author author) {
        List<String> books = author.getBooks()
                                   .stream()
                                   .map(Book::getName)
                                   .collect(Collectors.toList());

        return AuthorResponseDto.builder()
                                .id(author.getId())
                                .name(author.getName())
                                .birthDate(author.getBirthDate())
                                .country(author.getCountry())
                                .books(books)
                                .build();
    }
}
