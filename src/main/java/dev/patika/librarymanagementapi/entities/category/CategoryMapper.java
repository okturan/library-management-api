package dev.patika.librarymanagementapi.entities.category;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.book.Book;

public class CategoryMapper {
    public static CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        List<String> books = category.getBook()
                                     .stream()
                                     .map(Book::getName)
                                     .collect(Collectors.toList());

        return CategoryResponseDto.builder()
                                  .id(category.getId())
                                  .name(category.getName())
                                  .description(category.getDescription())
                                  .books(books)
                                  .build();
    }
}
