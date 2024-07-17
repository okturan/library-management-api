package dev.patika.librarymanagementapi.entities.author;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestDto {

    private String name;
    private Integer birthDate;
    private String country;
}
