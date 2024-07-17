package dev.patika.librarymanagementapi.entities.book;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.category.Category;

public class BookMapper {
    public static BookResponseDto BookToBookResponseDto(Book book) {

        List<String> categories = book.getCategory()
                                      .stream()
                                      .map(Category::getName)
                                      .collect(Collectors.toList());

        return BookResponseDto.builder()
                              .id(book.getId())
                              .name(book.getName())
                              .publicationYear(book.getPublicationYear())
                              .stock(book.getStock())
                              .authorName(book.getAuthor().getName())
                              .categories(categories)
                              .publisherName(book.getPublisher().getName())
                              .build();
    }
}
