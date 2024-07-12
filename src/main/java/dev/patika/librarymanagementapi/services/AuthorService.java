package dev.patika.librarymanagementapi.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.author.Author;
import dev.patika.librarymanagementapi.entities.author.AuthorMapper;
import dev.patika.librarymanagementapi.entities.author.AuthorResponseDto;
import dev.patika.librarymanagementapi.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                               .stream()
                               .map(AuthorMapper::AuthorToAuthorResponseDto)
                               .collect(Collectors.toList());
    }

    public AuthorResponseDto getAuthorById(int id) {
        Author author = authorRepository.findById(id)
                                        .orElseThrow(
                                                () -> new EntityNotFoundException("Author not found with id: " + id));
        return AuthorMapper.AuthorToAuthorResponseDto(author);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(int id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
