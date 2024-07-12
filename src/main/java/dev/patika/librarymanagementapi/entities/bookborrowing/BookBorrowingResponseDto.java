package dev.patika.librarymanagementapi.entities.bookborrowing;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookBorrowingResponseDto {

    private int id;
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private String bookName;
}
