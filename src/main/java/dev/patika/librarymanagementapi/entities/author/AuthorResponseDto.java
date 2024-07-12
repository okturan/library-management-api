package dev.patika.librarymanagementapi.entities.author;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorResponseDto {

    private int id;
    private String name;
    private int birthDate;
    private String country;
    private List<String> books;
}
