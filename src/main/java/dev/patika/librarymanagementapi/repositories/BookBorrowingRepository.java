package dev.patika.librarymanagementapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowing;

@Repository
public interface BookBorrowingRepository extends JpaRepository<BookBorrowing, Integer> {

}