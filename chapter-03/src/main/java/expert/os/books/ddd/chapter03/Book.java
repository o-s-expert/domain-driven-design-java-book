package expert.os.books.ddd.chapter03;

import java.time.Year;

public record Book(String isbn, String name, String author, int edition, Year year) {
}
