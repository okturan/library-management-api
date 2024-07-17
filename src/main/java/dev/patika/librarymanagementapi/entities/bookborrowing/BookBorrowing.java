package dev.patika.librarymanagementapi.entities.bookborrowing;

import java.time.LocalDate;

import dev.patika.librarymanagementapi.entities.book.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class BookBorrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String borrowerName;

    private LocalDate borrowingDate;

    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

}
