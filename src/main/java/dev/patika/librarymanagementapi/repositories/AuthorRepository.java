package dev.patika.librarymanagementapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.patika.librarymanagementapi.entities.author.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}