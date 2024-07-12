package dev.patika.librarymanagementapi.entities.publisher;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.book.Book;

public class PublisherMapper {
    public static PublisherResponseDto publisherToPublisherResponseDto(Publisher publisher) {
        List<String> books = publisher.getBooks()
                                      .stream()
                                      .map(Book::getName)
                                      .collect(Collectors.toList());

        return PublisherResponseDto.builder()
                                   .id(publisher.getId())
                                   .name(publisher.getName())
                                   .establishmentYear(publisher.getEstablishmentYear())
                                   .books(books)
                                   .build();
    }
}
