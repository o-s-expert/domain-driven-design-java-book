package expert.os.books.ddd.chapter03;

import java.time.Year;

public record Book(String isbn, String name, String author, int edition, Year year) {

    public Book newEdition(String isbn, Year year) {
        return new Book(isbn, name, author, edition + 1, year);
    }
}
