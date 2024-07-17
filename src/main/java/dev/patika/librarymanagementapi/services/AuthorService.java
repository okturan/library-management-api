package dev.patika.librarymanagementapi.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.author.Author;
import dev.patika.librarymanagementapi.entities.author.AuthorMapper;
import dev.patika.librarymanagementapi.entities.author.AuthorRequestDto;
import dev.patika.librarymanagementapi.entities.author.AuthorResponseDto;
import dev.patika.librarymanagementapi.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                               .stream()
                               .map(authorMapper::authorToAuthorResponseDto)
                               .collect(Collectors.toList());
    }

    public AuthorResponseDto getAuthorById(int id) {
        Author author = authorRepository.findById(id)
                                        .orElseThrow(
                                                () -> new EntityNotFoundException("Author not found with id: " + id));
        return authorMapper.authorToAuthorResponseDto(author);
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public AuthorResponseDto updateAuthor(int id, AuthorRequestDto authorRequestDto) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (optionalAuthor.isEmpty()) {
            throw new EntityNotFoundException("Author not found with id: " + id);
        }

        Author existingAuthor = optionalAuthor.get();
        Author mergedAauthor = authorMapper.updateAuthorFromDto(authorRequestDto, existingAuthor);

        return authorMapper.authorToAuthorResponseDto(authorRepository.save(mergedAauthor));
    }

    public void deleteAuthor(int id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
