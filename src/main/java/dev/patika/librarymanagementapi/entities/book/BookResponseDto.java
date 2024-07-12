package dev.patika.librarymanagementapi.entities.book;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponseDto {

    private int id;
    private String name;
    private int publicationYear;
    private int stock;
    private String authorName;
    private List<String> categories;
    private String publisherName;

}
