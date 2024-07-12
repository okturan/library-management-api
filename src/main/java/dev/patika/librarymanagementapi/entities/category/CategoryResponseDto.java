package dev.patika.librarymanagementapi.entities.category;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponseDto {

    private int id;
    private String name;
    private String description;
    private List<String> books;
}
