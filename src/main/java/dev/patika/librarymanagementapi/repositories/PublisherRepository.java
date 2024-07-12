package dev.patika.librarymanagementapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.patika.librarymanagementapi.entities.publisher.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}