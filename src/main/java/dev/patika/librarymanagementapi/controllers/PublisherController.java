package dev.patika.librarymanagementapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import dev.patika.librarymanagementapi.entities.publisher.Publisher;
import dev.patika.librarymanagementapi.entities.publisher.PublisherResponseDto;
import dev.patika.librarymanagementapi.services.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public List<PublisherResponseDto> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public PublisherResponseDto getPublisherById(@PathVariable int id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher createPublisher(@Valid @RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @PutMapping("/{id}")
    public Publisher updatePublisher(@PathVariable int id, @Valid @RequestBody Publisher publisher) {
        publisher.setId(id);
        return publisherService.savePublisher(publisher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
    }
}
