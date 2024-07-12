package dev.patika.librarymanagementapi.entities.publisher;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PublisherResponseDto {

    private int id;
    private String name;
    private int establishmentYear;
    private List<String> books;
}
