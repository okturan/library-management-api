package dev.patika.librarymanagementapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.patika.librarymanagementapi.entities.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}