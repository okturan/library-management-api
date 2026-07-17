package dev.patika.librarymanagementapi.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import dev.patika.librarymanagementapi.entities.publisher.Publisher;
import dev.patika.librarymanagementapi.entities.publisher.PublisherMapper;
import dev.patika.librarymanagementapi.entities.publisher.PublisherResponseDto;
import dev.patika.librarymanagementapi.repositories.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<PublisherResponseDto> getAllPublishers() {
        return publisherRepository.findAll()
                                  .stream()
                                  .map(PublisherMapper::publisherToPublisherResponseDto)
                                  .collect(Collectors.toList());
    }

    public PublisherResponseDto getPublisherById(int id) {
        Publisher publisher = publisherRepository.findById(id)
                                                 .orElseThrow(
                                                         () -> new EntityNotFoundException(
                                                                 "Publisher not found with id: " + id));
        return PublisherMapper.publisherToPublisherResponseDto(publisher);
    }

    @Transactional
    public Publisher savePublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Transactional
    public void deletePublisher(int id) {
        if (!publisherRepository.existsById(id)) {
            throw new EntityNotFoundException("Publisher not found with id: " + id);
        }
        publisherRepository.deleteById(id);
    }
}
