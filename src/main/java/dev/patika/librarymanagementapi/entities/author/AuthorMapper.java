package dev.patika.librarymanagementapi.entities.author;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import dev.patika.librarymanagementapi.entities.book.Book;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "books", source = "books")
    AuthorResponseDto authorToAuthorResponseDto(Author author);

    Author updateAuthorFromDto(AuthorRequestDto authorRequestDto, @MappingTarget Author author);

    default String map(Book value) {
        return value.getName();
    }
}
