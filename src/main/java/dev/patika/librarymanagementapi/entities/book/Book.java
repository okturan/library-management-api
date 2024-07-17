package dev.patika.librarymanagementapi.entities.book;

import java.util.List;

import dev.patika.librarymanagementapi.entities.author.Author;
import dev.patika.librarymanagementapi.entities.bookborrowing.BookBorrowing;
import dev.patika.librarymanagementapi.entities.category.Category;
import dev.patika.librarymanagementapi.entities.publisher.Publisher;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int publicationYear;

    private int stock;

    @ManyToOne
    private Author author;

    @ManyToMany
    private List<Category> category;

    @ManyToOne
    private Publisher publisher;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private List<BookBorrowing> borrowings;

}
