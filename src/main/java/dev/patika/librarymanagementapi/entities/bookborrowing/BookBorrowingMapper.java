package dev.patika.librarymanagementapi.entities.bookborrowing;

public class BookBorrowingMapper {
    public static BookBorrowingResponseDto bookBorrowingToBookBorrowingResponseDto(BookBorrowing bookBorrowing)
    {
        return BookBorrowingResponseDto.builder()
                                       .id(bookBorrowing.getId())
                                       .borrowerName(bookBorrowing.getBorrowerName())
                                       .borrowingDate(bookBorrowing.getBorrowingDate())
                                       .returnDate(bookBorrowing.getReturnDate())
                                       .bookName(bookBorrowing.getBook().getName())
                                       .build();
    }
}
