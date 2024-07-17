package dev.patika.librarymanagementapi.entities.publisher;

import java.util.List;

import dev.patika.librarymanagementapi.entities.book.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int establishmentYear;

    private String address;

    @OneToMany(mappedBy = "publisher", orphanRemoval = true)
    private List<Book> books;
}
